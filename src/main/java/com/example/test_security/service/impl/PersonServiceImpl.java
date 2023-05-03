package com.example.test_security.service.impl;

import com.example.test_security.exceptions.UserNotFoundException;
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
    public boolean existsByRequisite(String requisite) {
        return personRepository.findByRequisite(requisite).isPresent();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(
        () -> new UserNotFoundException("Person with this id not found.")
                );
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email)
                .orElseThrow(
        () -> new UsernameNotFoundException("Username does not exists.")
                );
    }

    @Override
    public Person findByRequisite(String requisite) {
        return personRepository.findByRequisite(requisite)
                .orElseThrow(
        () -> new UserNotFoundException("Person with this Requisite not exists.")
                );
    }
}
