package com.example.test_security.repository;

import com.example.test_security.model.BlockedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedPersonRepository
                        extends JpaRepository<BlockedPerson, Long> {
    Optional<BlockedPerson> findByRequisiteAndActual(String requisite, boolean actual);

    List<BlockedPerson> findDistinctByActual(boolean actual);
}
