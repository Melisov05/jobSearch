package com.example.demo.controller.user;

import com.example.demo.dto.user.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/candidates")
public class CandidateController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getCandidates(){
        return ResponseEntity.ok(userService.getCandidates());
    }

    @GetMapping("{name}")
    public ResponseEntity<List<UserDto>> getCandidatesByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getCandidatesByName(name));
    }
}
