package com.example.email.mapper;

import com.example.email.entity.Users;
import com.example.email.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(Users users) {
       return UserDTO.builder()
                .id(users.getId())
                .name(users.getName())
                .surname(users.getSurname())
                .email(users.getEmail())
                .build();
    }

    public Users toEntity(UserDTO userDTO){
        return  Users.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .build();
    }

}
