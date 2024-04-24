package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userDao.findUserByPhone(phoneNumber);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public Boolean isUserExists(String email) {
        return userDao.checkUserExistsByEmail(email);
    }

    @Override
    public String getEmailByUserId(Long id) {
        User user = userDao.findUserById(id).orElseThrow(() -> new UserNotFoundException("User with" +
                " id:" + id + " is not found"));
        String email = user.getEmail();
        return email;
    }

    @Override
    public List<UserDto> getCandidates() {
        List<User> list = userDao.getCandidates();
        List<UserDto> candidates = new ArrayList<>();
        for (User user : list){
            UserDto dto = toDto(user);
            candidates.add(dto);
        }
        return candidates;
    }

    @Override
    public List<UserDto> getEmployers() {
        List<User> list = userDao.getEmployers();
        List<UserDto> employers = new ArrayList<>();
        for (User user : list){
            UserDto dto = toDto(user);
            employers.add(dto);
        }
        return employers;
    }

    private UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }


}
