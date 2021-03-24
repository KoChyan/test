package org.example.haulmont.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //дата платежа
    @Column(name = "date")
    private Date date;

    //сумма платежа
    @Column(name = "amountOfPayment")
    private BigDecimal amountOfPayment;

    //сумма гашения тела кредита
    @Column(name = "principalRepaymentAmount")
    private BigDecimal principalRepaymentAmount;

    //сумма гашения процентов
    @Column(name = "interestRepaymentAmount")
    private BigDecimal interestRepaymentAmount;

    @ManyToOne
    @JoinColumn(name = "paymentSchedule_id")
    private PaymentSchedule paymentSchedule;


    public Payment() {
    }

    public Payment(Date date, BigDecimal amountOfPayment, BigDecimal principalRepaymentAmount, BigDecimal interestRepaymentAmount) {
        this.date = date;
        this.amountOfPayment = amountOfPayment;
        this.principalRepaymentAmount = principalRepaymentAmount;
        this.interestRepaymentAmount = interestRepaymentAmount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmountOfPayment() {
        return amountOfPayment;
    }

    public void setAmountOfPayment(BigDecimal amountOfPayment) {
        this.amountOfPayment = amountOfPayment;
    }

    public BigDecimal getPrincipalRepaymentAmount() {
        return principalRepaymentAmount;
    }

    public void setPrincipalRepaymentAmount(BigDecimal principalRepaymentAmount) {
        this.principalRepaymentAmount = principalRepaymentAmount;
    }

    public BigDecimal getInterestRepaymentAmount() {
        return interestRepaymentAmount;
    }

    public void setInterestRepaymentAmount(BigDecimal interestRepaymentAmount) {
        this.interestRepaymentAmount = interestRepaymentAmount;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule){
        this.paymentSchedule = paymentSchedule;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "date=" + date +
                ", amountOfPayment=" + amountOfPayment +
                ", principalRepaymentAmount=" + principalRepaymentAmount +
                ", interestRepaymentAmount=" + interestRepaymentAmount +
                '}';
    }
}
