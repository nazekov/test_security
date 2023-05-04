package com.example.test_security.service.impl;

import com.example.test_security.enums.Status;
import com.example.test_security.model.BlockedRequisite;
import com.example.test_security.model.Person;
import com.example.test_security.repository.BlockedRequisiteRepository;
import com.example.test_security.service.BlockedRequisiteService;
import com.example.test_security.service.PersonService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BlockedRequisiteServiceImpl implements BlockedRequisiteService {

    private final BlockedRequisiteRepository blockedRequisiteRepository;

    private final PersonService personService;

    public BlockedRequisiteServiceImpl(BlockedRequisiteRepository blockedRequisiteRepository,
                                       PersonService personService) {
        this.blockedRequisiteRepository = blockedRequisiteRepository;
        this.personService = personService;
    }

    @Override
    public BlockedRequisite update(Long personId, String email) {
//        Person person = personService.findById(personId);
//        Person personAdmin = personService.findByUsername(email);
//
//        BlockedRequisite blockedRequisite
//            = blockedPersonRepository.findByRequisiteAndActual(
//                person.getRequisite(), true
//            ).orElseThrow(
//    () -> new UserNotFoundException("This requisite not found")
//            );
//        Status newStatus = blockedRequisite.getStatus() == Status.BANNED
//                                        ? Status.ACTIVE : Status.BANNED;
//        blockedRequisite.setActual(false);
//        blockedPersonRepository.save(blockedRequisite);
//
//        person.setStatus(newStatus);
//        personService.save(person);
//
//        BlockedRequisite newBlockedRequisite = BlockedRequisite.builder()
//                    .requisite(blockedRequisite.getRequisite())
//                    .serviceId(blockedRequisite.getServiceId())
//                    .status(newStatus)
//                    .createdDate(blockedRequisite.getCreatedDate())
//                    .createdBy(blockedRequisite.getCreatedBy())
//                    .updatedDate(new Date())
//                    .updatedBy(personAdmin)
//                    .comment("Updated Status")
//                    .actual(true)
//                    .build();
//
//        return blockedPersonRepository.save(newBlockedRequisite);
        return null;
    }

//    @Override
//    public List<BlockedRequisite> findAllBlockedPerson() {
//        return blockedPersonRepository.findDistinctByActual(true);
//    }

    @Override
    public BlockedRequisite ban(BlockedRequisite blockedRequisite, String username) {

//        Person person = personService.findByRequisite(blockedRequisite.getRequisite());
        Person personAdmin = personService.findByUsername(username);

        Optional<BlockedRequisite> optionalBlockedRequisite
            = blockedRequisiteRepository.findByRequisiteAndServiceIdAndStatusAndActual(
                blockedRequisite.getRequisite(),
                blockedRequisite.getServiceId(),
                Status.BANNED,
                true);

        if (optionalBlockedRequisite.isEmpty()) {
            blockedRequisite.setStatus(Status.BANNED);
            blockedRequisite.setActual(true);
            blockedRequisiteRepository.save(blockedRequisite);

            blockedRequisiteRepository.findFirstByRequisiteAndServiceId(
                blockedRequisite.getRequisite(),
                blockedRequisite.getServiceId()
            ); //надо обдумать
        }

//        blockedRequisite.setActual(true);
//
//        Optional<BlockedRequisite> optionalBlockedPerson
//            = blockedPersonRepository.findByRequisiteAndActual(
//                blockedRequisite.getRequisite(), true
//            );
//
//        if (optionalBlockedPerson.isPresent()) {
//            BlockedRequisite blockedRequisiteExists = optionalBlockedPerson.get();
//            blockedRequisiteExists.setActual(false);
//
//            blockedRequisite.setCreatedDate(blockedRequisiteExists.getCreatedDate());
//            blockedRequisite.setCreatedBy(blockedRequisiteExists.getCreatedBy());
//            blockedRequisite.setUpdatedDate(new Date());
//            blockedRequisite.setUpdatedBy(personAdmin);
//
//            blockedPersonRepository.save(blockedRequisiteExists);
//        } else {
//            blockedRequisite.setCreatedDate(new Date());
//            blockedRequisite.setCreatedBy(personAdmin);
//        }
//
//        person.setStatus(Status.BANNED);
//        personService.save(person);
//
//        return blockedPersonRepository.save(blockedRequisite);
        return null;
    }
}
