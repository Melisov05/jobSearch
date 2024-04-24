package com.example.demo.service;

import com.example.demo.dto.user.EditUserDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User findUserByName(String name);
    User findUserByPhoneNumber(String phoneNumber);
    User findUserByEmail(String email);
    Optional<User> findUserById(Long id);
    Boolean isUserExists(String email);
    String getEmailByUserId(Long id);
    List<UserDto> getCandidates();

    List<UserDto> getEmployers();

    List<UserDto> getEmployersByName(String name);

    List<UserDto> getCandidatesByName(String name);
    void createUser(UserDto userDto);
    void editUser(EditUserDto editUserDto);
}
