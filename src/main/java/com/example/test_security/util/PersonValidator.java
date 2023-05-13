package com.example.test_security.util;

import com.example.test_security.model.Person;
import com.example.test_security.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personService.existsPersonByUsername(person.getUsername())) {
            errors.rejectValue("username", "",
                    "Человек с таким логином уже существует.");
        }
    }
}
