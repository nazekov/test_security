package com.example.test_security.service;

import com.example.test_security.model.Person;
import java.util.List;

public interface AdminService {

    Person add(Person person);

    List<Person> findAllPerson();
}
