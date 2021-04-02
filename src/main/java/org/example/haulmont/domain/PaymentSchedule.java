package org.example.haulmont.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "PAYMENT_SCHEDULE")
public class PaymentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "paymentSchedule", fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(mappedBy = "paymentSchedule", fetch = FetchType.LAZY)
    private CreditOffer creditOffer;

    public PaymentSchedule() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Payment> getPayments() {
        if(this.payments == null)
            return null;

        Collections.sort(this.payments);
        return this.payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setPaymentSchedule(this);
    }

    public void removePayment(Payment payment) {
        if (payment != null) {
            payments.remove(payment);
            payment.setPaymentSchedule(null);
        }
    }

    public Integer getAmountOfPayments() {
        return this.payments.size();
    }

    @Override
    public String toString() {
        return "PaymentSchedule{" +
                "payments=" + payments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentSchedule that = (PaymentSchedule) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
