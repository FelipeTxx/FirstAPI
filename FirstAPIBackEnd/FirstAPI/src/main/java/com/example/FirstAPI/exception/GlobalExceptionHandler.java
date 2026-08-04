package com.example.FirstAPI.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errorMsg = new HashMap<>();
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        for (FieldError error: errors){
            errorMsg.put(error.getField(),error.getDefaultMessage());
        }




        return ResponseEntity.badRequest().body(errorMsg);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(HabitNotFoundException.class)
    public ResponseEntity<String> handHabitNotFound(HabitNotFoundException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(HabitConclusionNotFoundException.class)
    public ResponseEntity<String> HabitConclusionAlreadyConcluedException(HabitConclusionNotFoundException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(HabitConclusionAlreadyConcluedException.class)
    public ResponseEntity<String> HabitConclusionAlreadyConcluedException(HabitConclusionAlreadyConcluedException ex){
        return ResponseEntity.status(409).body(ex.getMessage());
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String> EmailNotFoundException(EmailNotFoundException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(PasswordNotMatchesException.class)
    public ResponseEntity<String> PasswordNotMatchesException(PasswordNotMatchesException ex){
        return ResponseEntity.status(401).body(ex.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> AccessDeniedException(AccessDeniedException ex){
        return ResponseEntity.status(403).body(ex.getMessage());
    }

}
