package org.example.haulmont.service;

import org.example.haulmont.domain.Payment;
import org.example.haulmont.domain.PaymentSchedule;
import org.example.haulmont.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private PaymentScheduleService scheduleService;

    public void save(Payment payment) {
        paymentRepo.save(payment);
    }

    public void saveAll(List<Payment> payments, PaymentSchedule to){
        if(payments == null || to == null)
            return;

        payments.forEach(payment -> {
            payment.setPaymentSchedule(to);
            paymentRepo.save(payment);
        });
    }

    public void remove(Payment payment){
        paymentRepo.delete(payment);
    }


}
