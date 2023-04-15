package com.example.pastebin.exception;

public class InvalidParametersExeption extends RuntimeException{
    public InvalidParametersExeption(String message) {
        super(message);
    }
}