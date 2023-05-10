package com.example.test_security.service.impl;

import com.example.test_security.enums.Status;
import com.example.test_security.exceptions.RequisiteNotFoundException;
import com.example.test_security.model.BlockedRequisite;
import com.example.test_security.model.Person;
import com.example.test_security.repository.BlockedRequisiteRepository;
import com.example.test_security.service.BlockedRequisiteService;
import com.example.test_security.service.PersonService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
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
    public BlockedRequisite update(Long requisiteId, String username) {

        Person personAdmin = personService.findByUsername(username);

        BlockedRequisite blockedRequisiteFound
            = blockedRequisiteRepository.findById(requisiteId)
            .orElseThrow(
    () -> new RequisiteNotFoundException("Реквизит не найден")
            );
        blockedRequisiteFound.setActual(false);
        blockedRequisiteRepository.save(blockedRequisiteFound);

        String comment = blockedRequisiteFound.getStatus() == Status.BANNED
                        ? "Снят с бана" : "Забанен снова";

        Status newStatus = blockedRequisiteFound.getStatus() == Status.BANNED
                                                ? Status.ACTIVE : Status.BANNED;

        BlockedRequisite newBlockedRequisite = BlockedRequisite.builder()
                    .requisite(blockedRequisiteFound.getRequisite())
                    .serviceId(blockedRequisiteFound.getServiceId())
                    .status(newStatus)
                    .createdDate(blockedRequisiteFound.getCreatedDate())
                    .createdBy(blockedRequisiteFound.getCreatedBy())
                    .updatedDate(new Date())
                    .updatedBy(personAdmin)
                    .comment(comment)
                    .actual(true)
                    .build();

        return blockedRequisiteRepository.save(newBlockedRequisite);
    }

//    @Override
//    public List<BlockedRequisite> findAllBlockedPerson() {
//        return blockedPersonRepository.findDistinctByActual(true);
//    }

    @Override
    public BlockedRequisite ban(BlockedRequisite blockedRequisite, String username) {

        Person personAdmin = personService.findByUsername(username);

        List<BlockedRequisite> allByRequisiteAndServiceId = blockedRequisiteRepository
                .findAllByRequisiteAndServiceId(
                    blockedRequisite.getRequisite(),
                    blockedRequisite.getServiceId()
                );

        Date createdDate = new Date();
        Person createdBy = personAdmin;
        Date updatedDate = null;
        Person updatedBy = null;

        if (allByRequisiteAndServiceId.size() != 0) {
            BlockedRequisite requisiteFound = allByRequisiteAndServiceId.stream()
                    .filter(blockedRequisite1 -> blockedRequisite1.getActual() == true)
                    .findFirst()
                    .orElseThrow(() -> new RequisiteNotFoundException("Реквизит не найден"));
            requisiteFound.setActual(false);
            blockedRequisiteRepository.save(requisiteFound);

            createdDate = requisiteFound.getCreatedDate();
            createdBy = requisiteFound.getCreatedBy();
            updatedDate = new Date();
            updatedBy = personAdmin;
        }
        blockedRequisite.setCreatedDate(createdDate);
        blockedRequisite.setCreatedBy(createdBy);
        blockedRequisite.setUpdatedDate(updatedDate);
        blockedRequisite.setUpdatedBy(updatedBy);
        blockedRequisite.setStatus(Status.BANNED);
        blockedRequisite.setActual(true);

        return blockedRequisiteRepository.save(blockedRequisite);
    }

    @Override
    public Optional<BlockedRequisite> findAlreadyBannedRequisite(BlockedRequisite blockedRequisite) {
        return blockedRequisiteRepository.findByRequisiteAndServiceIdAndStatusAndActual(
                blockedRequisite.getRequisite(),
                blockedRequisite.getServiceId(),
                Status.BANNED,
                true
        );
    }

    @Override
    public List<BlockedRequisite> findAll() {
        return blockedRequisiteRepository.findAllByActualAndOrderByRequisite(true);
    }
}
