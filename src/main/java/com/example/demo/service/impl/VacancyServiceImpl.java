package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.exceptions.NoSuchVacancyFoundException;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void updateVacancy(VacancyDto vacancyDto) throws Exception {
        int from = vacancyDto.getExpFrom();
        int to = vacancyDto.getExpTo();

        if (to< from){
            throw new Exception("Xnj");
        }

        if (vacancyDto.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Salary cannot be negative.");
        }
        Vacancy vacancy = Vacancy.builder()
                .name(vacancyDto.getName())
                .id(vacancyDto.getId())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActive(vacancyDto.getIsActive())
                .build();

        vacancyDao.editVacancy(vacancy);
    }

    @Override
    public List<VacancyDto> getVacancies() {
        List<Vacancy> list = vacancyDao.getAllVacancies();
        return getListVacancy(list);
    }

    @Override
    public List<VacancyDto> getVacanciesByName(String name) {
        List<Vacancy> list = vacancyDao.getAllVacanciesByName(name);
        if(list.isEmpty()){
            throw new NoSuchVacancyFoundException("Vacancy not found or you entered wrong name");
        }
        return getListVacancy(list);
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(String category) {
        List<Vacancy> list = vacancyDao.findVacancyByCategory(category);
        if(list.isEmpty()){
            throw new NoSuchVacancyFoundException("Vacancy not found or you entered wrong category");
        }
        return getListVacancy(list);
    }

    @Override
    public boolean deleteVacancy(Long id) {
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

    private VacancyDto toDto(Vacancy model){
        return VacancyDto.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .categoryId(model.getCategoryId())
                .salary(model.getSalary())
                .expFrom(model.getExpFrom())
                .expTo(model.getExpTo())
                .isActive(model.getIsActive())
                .build();

    }

    private List<VacancyDto> getListVacancy(List<Vacancy> list){
        List<VacancyDto> vacancies = new ArrayList<>();

        for(Vacancy vacancy : list){
            Optional<User> user = userService.findUserById(vacancy.getId());
            String email = user.map(User::getEmail).orElse("Unknown");
            VacancyDto vacancyDto = toDto(vacancy);
            vacancyDto.setAuthorEmail(email);
            vacancies.add(vacancyDto);
        }
        return vacancies;
    }
}
