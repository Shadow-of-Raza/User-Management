package com.example.mahaswayam.service;

import com.example.mahaswayam.dto.UserDto;
import com.example.mahaswayam.model.User;
import com.example.mahaswayam.model.UserType;
import com.example.mahaswayam.repository.UserRepository;
import com.example.mahaswayam.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    /**
     * Creates a new user and assigns the corresponding user type.
     */
    @Override
    public UserDto createUser(UserDto userDto, String userTypeName) {
        User user = mapToEntity(userDto);

        // Fetch UserType from the database
        UserType userType = userTypeRepository.findByUserTypeName(userTypeName);
        if (userType == null) {
            throw new RuntimeException("Invalid userTypeName: " + userTypeName);
        }
        user.setUserType(userType);

        return mapToDto(userRepository.save(user));
    }

    /**
     * Retrieves a user by their unique ID.
     */
    @Override
    public UserDto getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    /**
     * Retrieves a list of all users.
     */
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates user details for a given user ID.
     */
    @Override
    public UserDto updateUser(UUID userId, UserDto userDto) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPasswordHash(userDto.getPassword());
            user.setPhone(userDto.getPhone());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAddress(userDto.getAddress());
            user.setCity(userDto.getCity());
            user.setState(userDto.getState());
            user.setPincode(userDto.getPincode());
            user.setProfilePictureUrl(userDto.getProfilePictureUrl());

            // Update user type if provided
            if (userDto.getUserTypeName() != null) {
                UserType userType = userTypeRepository.findByUserTypeName(userDto.getUserTypeName());
                user.setUserType(userType);
            }

            return mapToDto(userRepository.save(user));
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    /**
     * Deletes a user by their unique ID.
     */
    @Override
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
    
    /**
     * Converts UserDto to User entity.
     */
    private User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAadhaarNumber(userDto.getAadhaarNumber());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setPincode(userDto.getPincode());
        user.setProfilePictureUrl(userDto.getProfilePictureUrl());
        return user;
    }

    /**
     * Converts User entity to UserDto.
     */
    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAadhaarNumber(user.getAadhaarNumber());
        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setState(user.getState());
        userDto.setPincode(user.getPincode());
        userDto.setProfilePictureUrl(user.getProfilePictureUrl());

        if (user.getUserType() != null) {
            userDto.setUserTypeName(user.getUserType().getUserTypeName());
        }

        return userDto;
    }
}
