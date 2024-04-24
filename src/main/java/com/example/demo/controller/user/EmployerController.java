package com.example.demo.controller.user;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employer")
public class EmployerController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getEmployers(){
        return ResponseEntity.ok(userService.getEmployers());
    }

    @GetMapping("{name}")
    public ResponseEntity<List<UserDto>> getEmployersByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getEmployersByName(name));
    }

}
