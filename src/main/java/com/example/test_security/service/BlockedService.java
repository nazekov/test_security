package com.example.test_security.service;

import com.example.test_security.model.BlockedPerson;
import com.example.test_security.model.Person;

public interface BlockedService {

    BlockedPerson add(Person person);
}
