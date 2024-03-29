package com.example.demo.service;

import com.example.demo.dto.VacancyDto;

public interface VacancyService {
    void createVacancy(VacancyDto vacancyDto) throws Exception;

    boolean deleteVacancy(long id);
    void updateVacancy(VacancyDto vacancyDto) throws Exception;
}
