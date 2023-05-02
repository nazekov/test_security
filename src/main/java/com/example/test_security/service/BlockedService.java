package com.example.test_security.service;

import com.example.test_security.model.BlockedPerson;
import java.util.List;

public interface BlockedService {

    BlockedPerson add(Long personId, String email);

    List<BlockedPerson> findAllBlockedPerson();
}
