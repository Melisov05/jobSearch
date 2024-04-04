package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public HttpStatus updateVacancy(@RequestBody VacancyDto vacancyDto){
        try{
            vacancyService.updateVacancy(vacancyDto);
        } catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id){
        if(vacancyService.deleteVacancy(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<VacancyDto>> getVacancies(){
        try {
            return ResponseEntity.ok(vacancyService.getVacancies());
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{name}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByName(@PathVariable String name){
        return ResponseEntity.ok(vacancyService.getVacanciesByName(name));
    }


}
