package com.block7.block7crudvalidation.person.infrastructure.exception.entityNotFound;

public class EntityNotFoundException extends javax.persistence.EntityNotFoundException {
  String message;

  @Override
  public String getMessage() {
   return message;
  }

  public EntityNotFoundException(String message) {
   this.message = message;
  }
 }
