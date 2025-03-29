package com.example.mahaswayam.service;

import com.example.mahaswayam.model.UserType;
import java.util.List;
import java.util.Optional;

public interface UserTypeService {
    UserType createUserType(UserType userType);
    List<UserType> getAllUserTypes();
    Optional<UserType> getUserTypeById(Integer id);
    UserType updateUserType(Integer id, UserType userType);
    void deleteUserType(Integer id);
}
