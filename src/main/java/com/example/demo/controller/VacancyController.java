package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    public HttpStatus createVacancy(@RequestBody VacancyDto vacancyDto){
        try {
            vacancyService.createVacancy(vacancyDto);

        } catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.CREATED;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable long id){
        if(vacancyService.deleteMovie(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
