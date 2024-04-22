package com.example.demo.service;

import com.example.demo.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    void createVacancy(VacancyDto vacancyDto) throws Exception;

    boolean deleteVacancy(Long id);
    void updateVacancy(VacancyDto vacancyDto) throws Exception;

    List<VacancyDto> getVacancies() throws Exception;

    List<VacancyDto> getVacanciesByName(String name);

    List<VacancyDto> getVacanciesByCategory(String category);
}

