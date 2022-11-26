package com.block7.block7crudvalidation.person.application.exception;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;

public class EntityException {
    public static void onSave(Person person) {
        if (person.getUser().length() > 10 || person.getUser().length() < 6) throw new UnprocessableEntityException("User must be between 6 and 10 characters");

        if (person.getUser() == null ||
            person.getUser().isBlank()) throw new UnprocessableEntityException("User must not be null or empty");

        if (person.getPassword() == null ||
                person.getPassword().isBlank()) throw new UnprocessableEntityException("Password must not be null or empty");

        if (person.getName() == null ||
                person.getName().isBlank()) throw new UnprocessableEntityException("Name must not be null or empty");

        if (person.getEmail() == null ||
                person.getEmail().isBlank()) throw new UnprocessableEntityException("Email must not be null or empty");

        if (person.getCompanyEmail() == null ||
                person.getCompanyEmail().isBlank()) throw new UnprocessableEntityException("Company email must not be null or empty");

        if (person.getCity() == null ||
                person.getCity().isBlank()) throw new UnprocessableEntityException("City must not be null or empty");

        if (person.getActive() == null || person.getActive().toString().isBlank()) throw new UnprocessableEntityException("Active must not be null or empty");

        if (person.getImageUrl() == null || person.getImageUrl().toString().isBlank()) throw new UnprocessableEntityException("Image URL must not be null or empty");
    }
}
