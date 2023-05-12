package com.example.test_security.util.impl;

import com.example.test_security.model.Person;
import com.example.test_security.service.PersonService;
import com.example.test_security.util.ValidatePersonService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ValidatePersonServiceImpl implements ValidatePersonService {

    private final PersonService personService;

    public ValidatePersonServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean existsUsername(Person person) {
        return personService.existsPersonByUsername(person.getUsername());
    }
}
