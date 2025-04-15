package com.example.patient_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // handle exceptions globally for @notblank @notnull etc.
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <Map<String, String>> handleValidation(MethodArgumentNotValidException ex)
    {
        Map <String, String> errors = new HashMap <> ();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity <Map <String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex)
    {
        // show in terminal 
        log.warn("Email already exists: {}", ex.getMessage());

        Map <String, String> errors = new HashMap <> ();
        errors.put("message", "Email address already exists");

        return ResponseEntity.badRequest().body(errors);
    }

    // // Handle unique constraint violations (e.g., duplicate entry)
    // @ExceptionHandler(DataIntegrityViolationException.class)
    // public ResponseEntity<Map<String, String>> handleUniqueConstraintViolation(DataIntegrityViolationException ex) {
    //     Map<String, String> errors = new HashMap<>();
        
    //     // Customize error message
    //     String errorMessage = "Unique constraint violation - check if the data already exists.";
        
    //     // You can add more sophisticated checks here if you need to extract the specific constraint violation (e.g., email already exists)
    //     errors.put("error", errorMessage);
        
    //     // Return a 400 Bad Request response with a helpful error message
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    // }
}
