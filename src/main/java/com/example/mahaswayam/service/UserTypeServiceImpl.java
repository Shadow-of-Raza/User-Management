package com.example.mahaswayam.service;

import com.example.mahaswayam.model.UserType;
import com.example.mahaswayam.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserType createUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @Override
    public Optional<UserType> getUserTypeById(Integer id) {
        return userTypeRepository.findById(id);
    }

    @Override
    public UserType updateUserType(Integer id, UserType userType) {
        return userTypeRepository.findById(id).map(existingType -> {
            existingType.setUserTypeName(userType.getUserTypeName());
            return userTypeRepository.save(existingType);
        }).orElseThrow(() -> new RuntimeException("UserType not found with ID: " + id));
    }

    @Override
    public void deleteUserType(Integer id) {
        userTypeRepository.deleteById(id);
    }
}
