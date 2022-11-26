package com.block7.block7crudvalidation.shared.exception.entityNotFound;

import com.block7.block7crudvalidation.shared.exception.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@SuppressWarnings("ALL")
@ControllerAdvice
public class EntityNotFoundHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(
                e.getMessage(), notFound.toString(), new Date()
        );


        return new ResponseEntity<>(customError, notFound);
    }
}
