package com.example.mahaswayam.repository;

import com.example.mahaswayam.model.UserType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    UserType findByUserTypeName(String userTypeName);
}
