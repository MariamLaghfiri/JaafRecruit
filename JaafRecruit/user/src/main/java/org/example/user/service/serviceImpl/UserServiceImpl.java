package org.example.user.service.serviceImpl;

import org.example.user.dto.UserDTO;
import org.example.user.entity.User;
import org.example.user.repository.UserRepository;
import org.example.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<UserDTO> showAllUser(Long userId) {
        List<User> user = userRepository.findByDeletedFalse();
        return user.stream()
                .map(s -> modelMapper.map(s,UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user= userRepository.findByIdAndDeletedFalse(id).get();
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = userRepository.save(modelMapper.map(userDTO,User.class));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        userDTO.setId(id);
        User user = userRepository.save(modelMapper.map(userDTO, User.class));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public Boolean deleteUser(UserDTO userDTO) {
        userDTO.setDeleted(Boolean.TRUE);
        UserDTO userDTO1=modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)),UserDTO.class);
        return userDTO1.getDeleted();
    }
}
