package org.example.haulmont.repository;

import org.example.haulmont.domain.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentScheduleRepo extends JpaRepository<PaymentSchedule, UUID> {
    PaymentSchedule findByCreditOfferId(UUID id);
}
