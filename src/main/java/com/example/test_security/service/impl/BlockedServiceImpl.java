package com.example.test_security.service.impl;

import com.example.test_security.enums.Status;
import com.example.test_security.enums.UserService;
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
    public BlockedPerson add(Long personId, String email) {
        Person person = personService.findById(personId);
        Person personAdmin = personService.findByEmail(email);

        String requisite = null;
        UserService service = null;
        Status status = null;
        Date created = new Date();
        Person createdBy = personAdmin;
        Date updated = new Date();
        Person updatedBy = null;
        String comment = "Updated Status";

        Optional<BlockedPerson> personByRequisite
            = blockedPersonRepository.findByRequisiteAndActual(
                person.getRequisite(), true
            );

        if (personByRequisite.isPresent()) {
            BlockedPerson blockedPerson = personByRequisite.get();
            Status newStatus = blockedPerson.getStatus() == Status.BANNED
                                            ? Status.ACTIVE : Status.BANNED;

            requisite = blockedPerson.getRequisite();
            service = blockedPerson.getUserService(); //надо обдумать
            status = newStatus;
            created = blockedPerson.getCreatedDate();
            createdBy = blockedPerson.getCreatedBy();
            updatedBy = personAdmin;

            blockedPerson.setActual(false);
            blockedPersonRepository.save(blockedPerson);
            person.setStatus(newStatus);
        } else {
            requisite = person.getRequisite();
            service = UserService.INTERNET;
            status = Status.BANNED;
            updated = null;
            comment = "Banned";

            person.setStatus(Status.BANNED);
        }
        personService.save(person);

        BlockedPerson newBlockedPerson = BlockedPerson.builder()
                    .requisite(requisite)
                    .userService(service)
                    .status(status)
                    .createdDate(created)
                    .createdBy(createdBy)
                    .updatedDate(updated)
                    .updatedBy(updatedBy)
                    .comment(comment)
                    .actual(true)
                    .build();

        return blockedPersonRepository.save(newBlockedPerson);
    }

    @Override
    public List<BlockedPerson> findAllBlockedPerson() {
        return blockedPersonRepository.findDistinctByActual(true);
    }
}
