package com.example.demo.exception;

import java.util.NoSuchElementException;

public class ResumeNotFoundException extends NoSuchElementException {
    public ResumeNotFoundException() {
    }

    public ResumeNotFoundException(String s) {
        super(s);
    }
}
