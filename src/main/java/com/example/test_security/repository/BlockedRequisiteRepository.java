package com.example.test_security.repository;

import com.example.test_security.enums.ServiceId;
import com.example.test_security.enums.Status;
import com.example.test_security.model.BlockedRequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedRequisiteRepository
                        extends JpaRepository<BlockedRequisite, Long> {
    Optional<BlockedRequisite> findByRequisiteAndServiceIdAndStatusAndActual(
        String requisite, ServiceId serviceId, Status status, boolean actual
    );

    List<BlockedRequisite> findAllByRequisiteAndServiceId(String requisite, ServiceId serviceId);

    Optional<BlockedRequisite> findFirstByRequisiteAndServiceId(
        String requisite, ServiceId serviceId
    );

    List<BlockedRequisite> findDistinctByActual(boolean actual);

    @Query("FROM BlockedRequisite " +
            "WHERE actual = ?1 " +
            "ORDER BY requisite, serviceId")
    List<BlockedRequisite> findAllByActualAndOrderByRequisite(boolean actual);
}
