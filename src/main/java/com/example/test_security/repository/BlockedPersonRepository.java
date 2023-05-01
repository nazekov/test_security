package com.example.test_security.repository;

import com.example.test_security.model.BlockedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedPersonRepository
                        extends JpaRepository<BlockedPerson, Long> {

}
