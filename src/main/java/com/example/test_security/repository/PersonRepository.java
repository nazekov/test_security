package com.example.test_security.repository;

import com.example.test_security.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

//    Optional<Person> findFirstByRequisite(String requisite);

    Optional<Person> findById(Long id);

    Optional<Person> findByRequisite(String requisite);
}
