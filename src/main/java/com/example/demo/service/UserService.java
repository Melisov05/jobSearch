package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User findUserByName(String name);
    User findUserByPhoneNumber(String phoneNumber);
    User findUserByEmail(String email);
    boolean isUserExists(String email);
}
