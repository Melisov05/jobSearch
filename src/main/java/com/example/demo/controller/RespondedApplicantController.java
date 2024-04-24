package com.example.demo.controller;

import com.example.demo.dto.RespondedApplicantDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/respondedApplicants")
public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantsService;

    @GetMapping
    private ResponseEntity<List<RespondedApplicantDto>> getRespondedApplicants(){
        return ResponseEntity.ok(respondedApplicantsService.getRespondedApplicants());
    }

    @PostMapping
    private HttpStatus applyToVacancy(@RequestBody RespondedApplicantDto dto){
        respondedApplicantsService.applyToVacancy(dto);
        return HttpStatus.OK;
    }

}

