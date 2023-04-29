package com.example.test_security.service;

import com.example.test_security.model.Person;
import com.example.test_security.repository.BlockedPersonRepository;

public interface BlockedService {

    BlockedPersonRepository add(Person person);
}
