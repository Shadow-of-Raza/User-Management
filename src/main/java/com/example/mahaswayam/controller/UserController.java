package com.example.mahaswayam.controller;

import com.example.mahaswayam.dto.LoginRequest;
import com.example.mahaswayam.dto.UserDto;
import com.example.mahaswayam.model.User;
import com.example.mahaswayam.repository.UserRepository;
import com.example.mahaswayam.service.UserService;
import com.example.mahaswayam.service.UserServiceImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user.
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, 
                                              @RequestParam(name = "userTypeName", required = true) String userTypeName) {
        logger.info("Creating user: {}", userDto.getUsername());
        
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setUserTypeName(userTypeName);
        UserDto createdUser = userService.createUser(userDto, userTypeName);
        
        logger.info("User created successfully with ID: {}", createdUser.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    /**
     * Get user by ID.
     */
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        logger.info("Getting user by Id: {}", userId);
        
        try {
            UUID uuid = UUID.fromString(userId);
            UserDto userDto = userService.getUserById(uuid);
            return ResponseEntity.ok(userDto);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid UUID format: {}", userId);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get all users.
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Getting all users");
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Update user by ID.
     */
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, 
                                              @Valid @RequestBody UserDto userDto) {
        logger.info("Updating user: {}", userId);
        
        try {
            UUID uuid = UUID.fromString(userId);
            UserDto updatedUser = userService.updateUser(uuid, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid UUID format: {}", userId);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete user by ID.
     */
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        logger.info("Deleting user: {}", userId);
        
        try {
            UUID uuid = UUID.fromString(userId);
            userService.deleteUser(uuid);
            return ResponseEntity.ok("Deleted Successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid UUID format: {}", userId);
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Login user by User Name and Password.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        logger.info("User login attempt: {}", loginRequest.getUsername());

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid username or password");
        }

        logger.info("User logged in successfully: {}", user.getUsername());
        return ResponseEntity.ok("Login successful"); // Replace with JWT if implemented
    }

}
