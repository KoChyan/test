package org.example.haulmont.repository;

import org.example.haulmont.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankRepo extends JpaRepository<Bank, UUID> {
    Bank findByName(String name);

    List<Bank> findAll();
}
