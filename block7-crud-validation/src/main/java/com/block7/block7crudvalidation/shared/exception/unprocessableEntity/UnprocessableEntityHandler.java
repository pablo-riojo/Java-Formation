package com.block7.block7crudvalidation.shared.exception.unprocessableEntity;

import com.block7.block7crudvalidation.shared.exception.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class UnprocessableEntityHandler {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {UnprocessableEntityException.class})
    public ResponseEntity<Object> handleUnprocessableEntityException(UnprocessableEntityException e) {
        HttpStatus unprocessableEntity = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomError customError = new CustomError(
                e.getMessage(), unprocessableEntity.toString(), new Date()
        );

        return new ResponseEntity<>(customError, unprocessableEntity);
    }
}
