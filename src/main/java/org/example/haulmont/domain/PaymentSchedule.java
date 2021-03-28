package org.example.haulmont.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "paymentSchedule")
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "paymentSchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(mappedBy = "paymentSchedule")
    private CreditOffer creditOffer;

    public PaymentSchedule() {
    }

    public PaymentSchedule(List<Payment> payments) {
        this.payments = payments;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        if (payments != null)
            this.payments = payments;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    public void addPayment(Payment payment) {
        if (payment != null) {
            this.payments.add(payment);
            payment.setPaymentSchedule(this);
        }
    }

    @Override
    public String toString() {
        return "PaymentSchedule{" +
                "payments=" + payments +
                '}';
    }
}
