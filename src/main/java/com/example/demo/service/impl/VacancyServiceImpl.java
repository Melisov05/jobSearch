package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserService userService;

    @Override
    public void createVacancy(VacancyDto vacancyDto) throws Exception {
        Vacancy vacancy = fromDto(vacancyDto);

        vacancyDao.create(vacancy);
    }

    @Override
    public boolean deleteMovie(long id) {
        if(vacancyDao.getById(id).isPresent()){
            vacancyDao.deleteVacancy(id);
            return true;
        } else {
            return false;
        }
    }


    private Vacancy fromDto(VacancyDto dto) throws Exception{
        int from = dto.getExpFrom();
        int to = dto.getExpTo();

        User userByEmail = userService.findUserByEmail(dto.getAuthorEmail());
        Long authorId = userByEmail.getId();

        if (to< from){
            throw new Exception("Xnj");
        }

        if (dto.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Salary cannot be negative.");
        }

        return Vacancy.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .expFrom(dto.getExpFrom())
                .expTo(dto.getExpTo())
                .isActive(dto.getIsActive())
                .authorId(authorId)
                .build();
    }
}
