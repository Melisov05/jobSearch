package com.example.demo.exception;

import java.util.NoSuchElementException;

public class ContactNotFoundException extends NoSuchElementException {
    public ContactNotFoundException() {
    }

    public ContactNotFoundException(String s) {
        super(s);
    }
}
