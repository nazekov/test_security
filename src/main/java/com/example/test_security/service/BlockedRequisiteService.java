package com.example.test_security.service;

import com.example.test_security.model.BlockedRequisite;

public interface BlockedRequisiteService {

    BlockedRequisite update(Long personId, String username);

//    List<BlockedRequisite> findAllBlockedPerson();

    BlockedRequisite ban(BlockedRequisite blockedRequisite, String username);
}
