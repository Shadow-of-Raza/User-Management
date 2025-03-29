package com.example.mahaswayam.service;

import java.util.List;
import java.util.UUID;

import com.example.mahaswayam.dto.UserDto;

public interface UserService {

    /**
     * Create a new user with a specified user type.
     *
     * @param userDto      The user data transfer object.
     * @param userTypeName The name of the user type.
     * @return The created UserDto.
     */
    UserDto createUser(UserDto userDto, String userTypeName);

    /**
     * Retrieve a user by their unique ID.
     *
     * @param userId The unique identifier of the user.
     * @return An Optional containing the UserDto if found.
     */
    UserDto getUserById(UUID userId);  // Optional to avoid null issues

    /**
     * Get a list of all users.
     *
     * @return A list of UserDto objects.
     */
    List<UserDto> getAllUsers();

    /**
     * Update an existing user's details.
     *
     * @param userId  The unique identifier of the user.
     * @param userDto The updated user data.
     * @return The updated UserDto.
     */
    UserDto updateUser(UUID userId, UserDto userDto);

    /**
     * Delete a user by their unique ID.
     *
     * @param userId The unique identifier of the user.
     */
    void deleteUser(UUID userId);
}
