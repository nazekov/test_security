package com.example.test_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedPersonRepository
                        extends JpaRepository<BlockedPersonRepository, Long> {


}
