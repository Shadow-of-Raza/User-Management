package com.example.mahaswayam.service;

import java.util.List;
import java.util.UUID;

import com.example.mahaswayam.dto.UserDto;

public interface UserService {

  
    UserDto createUser(UserDto userDto, String userTypeName);


    UserDto getUserById(UUID userId);  

    List<UserDto> getAllUsers();


    UserDto updateUser(UUID userId, UserDto userDto);

 
    void deleteUser(UUID userId);
}
