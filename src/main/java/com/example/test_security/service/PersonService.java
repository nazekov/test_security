package com.example.test_security.service;

import com.example.test_security.model.Person;
import java.util.List;

public interface PersonService {

    Person add(Person person);

    boolean existsByRequisite(String requisite);

    List<Person> findAll();
}
