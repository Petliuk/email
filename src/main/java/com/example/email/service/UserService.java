package com.example.email.service;

import com.example.email.entity.Users;
import com.example.email.model.UserDTO;

import java.util.List;

public interface UserService {

    public UserDTO addNewUser(UserDTO userDTO);
    public void deleteUserById(Long id);
    public List<UserDTO> getAllUsers();
}
