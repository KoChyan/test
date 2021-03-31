package org.example.haulmont.dao;

import org.example.haulmont.domain.Payment;
import org.example.haulmont.domain.PaymentSchedule;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PaymentScheduleDAO {

    @PersistenceContext
    EntityManager em;

    public void remove(Payment payment){
        PaymentSchedule schedule = em.find(PaymentSchedule.class, payment.getPaymentSchedule().getId());
        schedule.removePayment(payment);
    }
}
