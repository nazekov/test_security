package com.example.test_security.service.impl;

import com.example.test_security.model.Person;
import com.example.test_security.repository.PersonRepository;
import com.example.test_security.service.PersonService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username)
                .orElseThrow(
        () -> new UsernameNotFoundException("Username does not exists.")
                );
    }

    @Override
    public boolean existsPersonByUsername(String username) {
        return personRepository.existsPersonByUsername(username);
    }
}
