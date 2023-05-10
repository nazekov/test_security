package com.example.test_security.util;

import com.example.test_security.model.Person;

import java.util.Optional;

public interface ValidatePersonService {

    Optional<Person> findByUsername(Person person);
}
