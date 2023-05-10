package com.example.test_security.util;

import com.example.test_security.model.BlockedRequisite;
import java.util.Optional;

public interface ValidateRequisiteService {

    Optional<BlockedRequisite> findAlreadyBannedRequisite(BlockedRequisite blockedRequisite);
}
