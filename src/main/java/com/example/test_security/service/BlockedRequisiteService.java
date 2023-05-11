package com.example.test_security.service;

import com.example.test_security.enums.ServiceId;
import com.example.test_security.model.BlockedRequisite;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface BlockedRequisiteService {

    BlockedRequisite update(Long requisiteId, String username);

    BlockedRequisite ban(BlockedRequisite blockedRequisite, String username);

    Optional<BlockedRequisite> findAlreadyBannedRequisite(BlockedRequisite blockedRequisite);

    List<BlockedRequisite> findAll();

    ResponseEntity<?> getStatus(String requisite, ServiceId serviceId);
}
