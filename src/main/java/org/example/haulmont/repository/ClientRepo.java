package org.example.haulmont.repository;

import org.example.haulmont.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {
    List<Client> findAllByName(String s);

    List<Client> findAllBySurname(String s);

    List<Client> findAllByPatronymic(String s);

    List<Client> findAllByEmail(String s);

    List<Client> findAllByPhone(String s);

    List<Client> findAllByPassportNumber(String s);

}
