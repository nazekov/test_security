package com.example.test_security.service;

import com.example.test_security.model.BlockedRequisite;

import java.util.List;
import java.util.Optional;

public interface BlockedRequisiteService {

    BlockedRequisite update(Long requisiteId, String username);

//    List<BlockedRequisite> findAllBlockedPerson();

    BlockedRequisite ban(BlockedRequisite blockedRequisite, String username);

    Optional<BlockedRequisite> findAlreadyBannedRequisite(BlockedRequisite blockedRequisite);

    List<BlockedRequisite> findAll();
}
