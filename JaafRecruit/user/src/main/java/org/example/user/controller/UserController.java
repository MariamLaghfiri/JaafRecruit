package org.example.user.controller;

import org.example.user.dto.UserDTO;
import org.example.user.dto.UserKeycloakRequestDto;
import org.example.user.service.KeycloakService;
import org.example.user.service.UserService;
import org.example.user.service.serviceImpl.KeycloakServiceImpl;
import org.example.user.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    KeycloakService keycloakService;

    @Autowired
    public UserController(KeycloakServiceImpl keycloakService, UserServiceImpl userService) {
        this.keycloakService = keycloakService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> showAllUser(@RequestHeader Long userId){
        List<UserDTO> userDTOS=userService.showAllUser(userId);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id){
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        userDTO.setRegistrationDate(LocalDate.now());
        userDTO.setDeleted(false);
        String userKeycloakId = createUserInKeycloak(userDTO);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);
        userDTO.setUserKeycloakId(userKeycloakId);
        keycloakService.assignRoleToUser(userKeycloakId, userDTO.getRole());
        UserDTO user=userService.addUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private String createUserInKeycloak(UserDTO userDTO){
        UserKeycloakRequestDto userKeycloakRequestDto = UserKeycloakRequestDto.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .lastName(userDTO.getLastName())
                .firstName(userDTO.getFirstName())
                .password(userDTO.getPassword()).build();
        return keycloakService.createUserInKeycloak(userKeycloakRequestDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        UserDTO user=userService.updateUser(id, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public Map<String,Boolean> deleteUser(@PathVariable(value = "id") Long id){
        UserDTO userDTO=userService.getUserById(id);
        Map<String,Boolean> response=new HashMap<>();
        if(Boolean.TRUE.equals(userService.deleteUser(userDTO))){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}