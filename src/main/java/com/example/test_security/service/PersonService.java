package com.example.test_security.service;

import com.example.test_security.model.Person;
import java.util.List;

public interface PersonService {

    Person save(Person person);

    List<Person> findAll();

    Person findByUsername(String username);

    boolean existsPersonByUsername(String username);
}
