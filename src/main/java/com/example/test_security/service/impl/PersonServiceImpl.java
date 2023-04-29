package com.example.test_security.service.impl;

import com.example.test_security.model.Person;
import com.example.test_security.repository.PersonRepository;
import com.example.test_security.service.PersonService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public boolean existsByRequisite(String requisite) {
        return personRepository.findFirstByRequisite(requisite).isPresent();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
