package org.example.haulmont.repository;

import org.example.haulmont.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {
    Set<Client> findAllByName(String s);

    Set<Client> findAllBySurname(String s);

    Set<Client> findAllByPatronymic(String s);

    Set<Client> findAllByEmail(String s);

    Set<Client> findAllByPhone(String s);

    Set<Client> findAllByPassportId(String s);

}
