package com.example.mahaswayam.controller;

import com.example.mahaswayam.model.UserType;
import com.example.mahaswayam.service.UserTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usertypes")
public class UserTypeController {

    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @PostMapping("/createUserType")
    public ResponseEntity<UserType> createUserType(@RequestBody UserType userType) {
        return ResponseEntity.ok(userTypeService.createUserType(userType));
    }

    @GetMapping("/getAllUserTypes")
    public ResponseEntity<List<UserType>> getAllUserTypes() {
        return ResponseEntity.ok(userTypeService.getAllUserTypes());
    }

    @GetMapping("/getUserTypeById/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable Integer id) {
        return userTypeService.getUserTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateUserType/{id}")
    public ResponseEntity<UserType> updateUserType(@PathVariable Integer id, @RequestBody UserType userType) {
        return ResponseEntity.ok(userTypeService.updateUserType(id, userType));
    }

    @DeleteMapping("/deleteUserType/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Integer id) {
        userTypeService.deleteUserType(id);
        return ResponseEntity.noContent().build();
    }
}
