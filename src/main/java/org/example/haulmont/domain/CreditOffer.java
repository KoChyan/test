package org.example.haulmont.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creditOffer")
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    //сумма кредита
    @Column(name = "credit_amount")
    private BigDecimal creditAmount;

    //график платежей
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_schedule_id")
    private PaymentSchedule paymentSchedule;


    public CreditOffer() {
    }

    public CreditOffer(Client client, Credit credit, BigDecimal creditAmount, PaymentSchedule paymentSchedules) {
        this.client = client;
        this.credit = credit;
        this.creditAmount = creditAmount;
        this.paymentSchedule = paymentSchedules;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    @Override
    public String toString() {
        return "CreditOffer{" +
                "client=" + client +
                ", credit=" + credit +
                ", creditAmount=" + creditAmount +
                ", paymentSchedule=" + paymentSchedule +
                '}';
    }
}
