package com.example.demo.exception;

import java.util.NoSuchElementException;

public class NoSuchVacancyFoundException extends NoSuchElementException {
    public NoSuchVacancyFoundException(String s) {
        super(s);
    }

    public NoSuchVacancyFoundException() {
    }
}
