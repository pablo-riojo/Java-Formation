package com.block7.block7crudvalidation.person.infrastructure.exception.entityNotFound;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
