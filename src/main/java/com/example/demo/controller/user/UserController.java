package com.example.demo.controller.user;

import com.example.demo.dto.user.EditUserDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public HttpStatus createUser(UserDto userDto){
        userService.createUser(userDto);
        log.info("Received DTO to create: {}", userDto);
        return HttpStatus.CREATED;
    }

    @PostMapping ("/edit")
    public HttpStatus editUser(EditUserDto editUserDto){
        userService.editUser(editUserDto);
        log.info("Received DTO for edit: {}", editUserDto);
        return HttpStatus.OK;
    }
}
