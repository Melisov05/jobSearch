package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User findUserByName(String name);
    User findUserByPhoneNumber(String phoneNumber);
    User findUserByEmail(String email);
    Optional<User> findUserById(Long id);
    boolean isUserExists(String email);

    List<UserDto> getCandidates();
}
