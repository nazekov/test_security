package com.example.test_security.service.impl;

import com.example.test_security.model.Person;
import com.example.test_security.service.AdminService;
import com.example.test_security.service.PersonService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(PersonService personService,
                            PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Person add(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personService.save(person);
    }

    @Override
    public List<Person> findAllPerson() {
        return personService.findAll();
    }
}
