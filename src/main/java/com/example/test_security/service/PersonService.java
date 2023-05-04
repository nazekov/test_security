package com.example.test_security.service;

import com.example.test_security.model.Person;
import java.util.List;

public interface PersonService {

    Person save(Person person);

//    boolean existsByRequisite(String requisite);

    List<Person> findAll();

//    Person findById(Long id);
//
    Person findByUsername(String username);
//
//    Person findByRequisite(String requisite);
}
