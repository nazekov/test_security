package com.example.test_security.service.impl;

import com.example.test_security.model.BlockedPerson;
import com.example.test_security.model.Person;
import com.example.test_security.repository.BlockedPersonRepository;
import com.example.test_security.service.BlockedService;
import org.springframework.stereotype.Service;

@Service
public class BlockedServiceImpl implements BlockedService {

    private final BlockedPersonRepository blockedPersonRepository;

    public BlockedServiceImpl(BlockedPersonRepository blockedPersonRepository) {
        this.blockedPersonRepository = blockedPersonRepository;
    }

    @Override
    public BlockedPerson add(Person person) {
        return blockedPersonRepository.save(null);
    }
}
