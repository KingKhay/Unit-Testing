package com.khaydev.UnitTesting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({StudentNotFoundException.class})
    private ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex, WebRequest request){
        Map<String, Object> error = new HashMap<>();
        error.put("message", "Student Not Found");
        error.put("status", HttpStatus.NOT_FOUND);
        error.put("timestamp", LocalDate.now());

        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
