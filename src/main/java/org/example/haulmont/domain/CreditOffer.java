package org.example.haulmont.domain;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(name = "creditAmount")
    private Long creditAmount;

    //график платежей
    @OneToMany(mappedBy = "creditOffer", fetch = FetchType.LAZY)
    private List<PaymentSchedule> paymentSchedules = new ArrayList<>();


    public CreditOffer() {
    }

    public CreditOffer(Client client, Credit credit, Long creditAmount, List<PaymentSchedule> paymentSchedules) {
        this.client = client;
        this.credit = credit;
        this.creditAmount = creditAmount;
        this.paymentSchedules = paymentSchedules;
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

    public Long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public List<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
        if (paymentSchedules != null)
            this.paymentSchedules = paymentSchedules;
    }

    public void addPaymentSchedule(PaymentSchedule paymentSchedule) {
        if (paymentSchedule != null) {
            this.paymentSchedules.add(paymentSchedule);
            paymentSchedule.setCreditOffer(this);
        }
    }

    @Override
    public String toString() {
        return "CreditOffer{" +
                "client=" + client +
                ", credit=" + credit +
                ", creditAmount=" + creditAmount +
                ", paymentSchedules=" + paymentSchedules +
                '}';
    }
}
