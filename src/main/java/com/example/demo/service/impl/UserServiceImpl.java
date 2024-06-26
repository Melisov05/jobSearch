package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.user.EditUserDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

//    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userDao.findUserByPhone(phoneNumber);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
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

    @Override
    public List<UserDto> getEmployersByName(String name) {
        List<User> list = userDao.getEmployersByName(name);
        List<UserDto> employersByName = new ArrayList<>();
        for (User user : list){
            UserDto dto = toDto(user);
            employersByName.add(dto);
        }
        return employersByName;
    }

    @Override
    public List<UserDto> getCandidatesByName(String name) {
        List<User> list = userDao.getCandidatesByName(name);
        List<UserDto> candidatesByName = new ArrayList<>();
        for (User user : list){
            UserDto dto = toDto(user);
            candidatesByName.add(dto);
        }
        return candidatesByName;
    }

    @Override
    public void createUser(UserDto userDto){
        Optional<User> check = findUserByEmail(userDto.getEmail());
        if(check.isPresent()){
            throw new IllegalStateException("User already exists with email: " + userDto.getEmail());
        }
        User user = fromDto(userDto);
        userDao.createUser(user);
    }

    @Override
    public void editUser(EditUserDto editUserDto){
        String fileName = null;
        if (!editUserDto.getAvatar().isEmpty()) {
            if (Objects.requireNonNull(editUserDto.getAvatar().getContentType()).matches("png|jpeg|jpg")) {
                throw new IllegalArgumentException("Unsupported img types (should be: \"png|jpeg|jpg\")");
            }
            fileName = FileUtil.saveUploadedFile(editUserDto.getAvatar(), "/images");
        }
        User user = User.builder()
                .name(editUserDto.getName())
                .surname(editUserDto.getSurname())
                .email(editUserDto.getEmail())
                .password(editUserDto.getPassword())
                .phoneNumber(editUserDto.getPhoneNumber())
                .avatar(fileName)
                .accountType(editUserDto.getAccountType())
                .age(editUserDto.getAge())
                .build();
        userDao.updateProfile(user);
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

    private User fromDto(UserDto userDto){
        return  User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(null)
                .accountType(userDto.getAccountType())
                .age(userDto.getAge())
                .email(userDto.getPhoneNumber())
                .build();
    }


}
