package org.example.haulmont.service;

import org.example.haulmont.domain.Payment;
import org.example.haulmont.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public void save(Payment payment){
        paymentRepo.save(payment);
    }
}
