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
//        Person createdBy = null;
//        Person updatedBy = null;

//        BlockedPerson newBlockedPerson = null;

        String requisite = null;
        UserService service = null;
        Status status = null;
        Date created = new Date();
        Person createdBy = personAdmin;
        Date updated = new Date();
        Person updatedBy = null;
        String comment = null;

        Optional<BlockedPerson> personByRequisite
                = blockedPersonRepository.findByRequisite(person.getRequisite());

        if (personByRequisite.isPresent()) {
            BlockedPerson blockedPerson = personByRequisite.get();
            Status newStatus = blockedPerson.getStatus() == Status.BANNED
                                            ? Status.ACTIVE : Status.BANNED;
//            newBlockedPerson = BlockedPerson.builder()
//                    .requisite(blockedPerson.getRequisite())
//                    .userService(blockedPerson.getUserService())
//                    .status(newStatus)
//                    .createdDate(blockedPerson.getCreatedDate())
//                    .createdBy(blockedPerson.getCreatedBy())
//                    .updatedDate(new Date())
//                    .updatedBy(updatedBy)
//                    .comment("Updated Status")
//                    .build();

            requisite = blockedPerson.getRequisite();
            service = blockedPerson.getUserService(); //надо обдумать
            status = newStatus;
            created = blockedPerson.getCreatedDate();
            createdBy = blockedPerson.getCreatedBy();
            comment = "Updated Status";
            updatedBy = personAdmin;

            person.setStatus(newStatus);
        } else {
//            createdBy = personAdmin;
//            newBlockedPerson = BlockedPerson.builder()
//                    .requisite(person.getRequisite())
//                    .userService(UserService.INTERNET)
//                    .status(Status.BANNED)
//                    .createdDate(new Date())
//                    .createdBy(createdBy)
//                    .updatedDate(null)
//                    .updatedBy(null)
//                    .comment("Banned")
//                    .build();
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
                    .build();

        return blockedPersonRepository.save(newBlockedPerson);
    }

    @Override
    public List<BlockedPerson> findAllBlockedPerson() {
        List<BlockedPerson> all = blockedPersonRepository.findAll();
        return all;
    }
}
