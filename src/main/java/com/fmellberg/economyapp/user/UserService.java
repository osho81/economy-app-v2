package com.fmellberg.economyapp.user;

import com.fmellberg.economyapp.user.DTO.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(int id);

    UserDTO getUserByUserName(String username);
}
