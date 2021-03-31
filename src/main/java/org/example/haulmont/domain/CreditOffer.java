package org.example.haulmont.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "CREDIT_OFFER")
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Column(name = "overpayment")
    private BigDecimal overpayment;

    @Column(name = "credit_amount")
    private BigDecimal creditAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "payment_schedule_id")
    private PaymentSchedule paymentSchedule;


    public CreditOffer() {
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

    public BigDecimal getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(BigDecimal overpayment) {
        this.overpayment = overpayment;
    }

    @Override
    public String toString() {
        return "CreditOffer{" +
                "client=" + client +
                ", credit=" + credit +
                ", overpayment=" + overpayment +
                ", creditAmount=" + creditAmount +
                ", paymentSchedule=" + paymentSchedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditOffer that = (CreditOffer) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
