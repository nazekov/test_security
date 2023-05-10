package com.example.test_security.util.impl;

import com.example.test_security.model.BlockedRequisite;
import com.example.test_security.service.BlockedRequisiteService;
import com.example.test_security.util.ValidateRequisiteService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ValidateRequisiteServiceImpl implements ValidateRequisiteService {

    private final BlockedRequisiteService blockedRequisiteService;

    public ValidateRequisiteServiceImpl(BlockedRequisiteService blockedRequisiteService) {
        this.blockedRequisiteService = blockedRequisiteService;
    }

    @Override
    public Optional<BlockedRequisite> findAlreadyBannedRequisite(BlockedRequisite blockedRequisite) {
        return blockedRequisiteService.findAlreadyBannedRequisite(blockedRequisite);
    }
}
