package com.block7.block7crudvalidation.person.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class CustomError {
private final String message;
private final String httpStatus;
private final Date timestamp;
}

