package com.example.test_security.service.impl;

import com.example.test_security.enums.Status;
import com.example.test_security.exceptions.UserNotFoundException;
import com.example.test_security.model.BlockedPerson;
import com.example.test_security.model.Person;
import com.example.test_security.repository.BlockedPersonRepository;
import com.example.test_security.service.BlockedService;
import com.example.test_security.service.PersonService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlockedServiceImpl implements BlockedService {

    private final BlockedPersonRepository blockedPersonRepository;

    private final PersonService personService;

    public BlockedServiceImpl(BlockedPersonRepository blockedPersonRepository,
                              PersonService personService) {
        this.blockedPersonRepository = blockedPersonRepository;
        this.personService = personService;
    }

    @Override
    public BlockedPerson update(Long personId, String email) {
        Person person = personService.findById(personId);
        Person personAdmin = personService.findByEmail(email);

        BlockedPerson blockedPerson
            = blockedPersonRepository.findByRequisiteAndActual(
                person.getRequisite(), true
            ).orElseThrow(
    () -> new UserNotFoundException("This requisite not found")
            );
        Status newStatus = blockedPerson.getStatus() == Status.BANNED
                                        ? Status.ACTIVE : Status.BANNED;
        blockedPerson.setActual(false);
        blockedPersonRepository.save(blockedPerson);

        person.setStatus(newStatus);
        personService.save(person);

        BlockedPerson newBlockedPerson = BlockedPerson.builder()
                    .requisite(blockedPerson.getRequisite())
                    .userService(blockedPerson.getUserService())
                    .status(newStatus)
                    .createdDate(blockedPerson.getCreatedDate())
                    .createdBy(blockedPerson.getCreatedBy())
                    .updatedDate(new Date())
                    .updatedBy(personAdmin)
                    .comment("Updated Status")
                    .actual(true)
                    .build();

        return blockedPersonRepository.save(newBlockedPerson);
    }

    @Override
    public List<BlockedPerson> findAllBlockedPerson() {
        return blockedPersonRepository.findDistinctByActual(true);
    }

    @Override
    public BlockedPerson ban(BlockedPerson blockedPerson, String email) {
        Person person = personService.findByRequisite(blockedPerson.getRequisite());
        Person personAdmin = personService.findByEmail(email);

        blockedPerson.setStatus(Status.BANNED);
        blockedPerson.setActual(true);

        Optional<BlockedPerson> optionalBlockedPerson
            = blockedPersonRepository.findByRequisiteAndActual(
                blockedPerson.getRequisite(), true
            );

        if (optionalBlockedPerson.isPresent()) {
            BlockedPerson blockedPersonExists = optionalBlockedPerson.get();
            blockedPersonExists.setActual(false);

            blockedPerson.setCreatedDate(blockedPersonExists.getCreatedDate());
            blockedPerson.setCreatedBy(blockedPersonExists.getCreatedBy());
            blockedPerson.setUpdatedDate(new Date());
            blockedPerson.setUpdatedBy(personAdmin);

            blockedPersonRepository.save(blockedPersonExists);
        } else {
            blockedPerson.setCreatedDate(new Date());
            blockedPerson.setCreatedBy(personAdmin);
        }

        person.setStatus(Status.BANNED);
        personService.save(person);

        return blockedPersonRepository.save(blockedPerson);
    }
}
