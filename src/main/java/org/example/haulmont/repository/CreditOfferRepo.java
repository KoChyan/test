package org.example.haulmont.repository;

import org.example.haulmont.domain.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditOfferRepo extends JpaRepository<CreditOffer, UUID> {

    List<CreditOffer> findByClientId(UUID id);

    List<CreditOffer> findByCreditId(UUID id);
}
