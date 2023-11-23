package com.example.email.service.impl;

import com.example.email.entity.Users;
import com.example.email.mapper.UserMapper;
import com.example.email.model.UserDTO;
import com.example.email.repository.UserRepository;
import com.example.email.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDTO addNewUser(UserDTO userDTO)  {

        Users user = userMapper.toEntity(userDTO);
        Users savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }



}
