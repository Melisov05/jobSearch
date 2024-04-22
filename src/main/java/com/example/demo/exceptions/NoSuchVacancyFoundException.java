package com.example.demo.exceptions;

import java.util.NoSuchElementException;

public class NoSuchVacancyFoundException extends NoSuchElementException {
    public NoSuchVacancyFoundException(String s) {
        super(s);
    }

    public NoSuchVacancyFoundException() {
    }
}
