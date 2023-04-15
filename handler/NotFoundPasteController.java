package com.example.pastebin.handler;

import com.example.pastebin.exception.InvalidParametersExeption;
import com.example.pastebin.exception.PasteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundPasteController {
    @ExceptionHandler(PasteNotFoundException.class)
    public ResponseEntity<?> notFound() {
        return ResponseEntity.status(404).body("Такой не найден");
    }

    @ExceptionHandler(InvalidParametersExeption.class)
    public ResponseEntity<?> invalidParam() {
        return ResponseEntity.status(400).body("Неверно введены данные");
    }

}