package org.example.user.service;

import org.example.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> showAllUser(Long id_user);

    UserDTO getUserById(Long id);
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(Long id ,UserDTO userDTO);
    Boolean deleteUser(UserDTO userDTO);
}
