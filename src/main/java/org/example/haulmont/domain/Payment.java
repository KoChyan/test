package org.example.haulmont.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //дата платежа
    @Column(name = "date")
    private Date date;

    //сумма платежа
    @Column(name = "amount_of_payment")
    private BigDecimal amountOfPayment;

    //сумма гашения тела кредита
    @Column(name = "principal_repayment_amount")
    private BigDecimal principalRepaymentAmount;

    //сумма гашения процентов
    @Column(name = "percent_repayment_amount")
    private BigDecimal percentRepaymentAmount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_schedule_id")
    private PaymentSchedule paymentSchedule;


    public Payment() {
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

    public BigDecimal getPercentRepaymentAmount() {
        return percentRepaymentAmount;
    }

    public void setPercentRepaymentAmount(BigDecimal percentRepaymentAmount) {
        this.percentRepaymentAmount = percentRepaymentAmount;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule){
        this.paymentSchedule = paymentSchedule;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "date=" + date +
                ", amountOfPayment=" + amountOfPayment +
                ", principalRepaymentAmount=" + principalRepaymentAmount +
                ", interestRepaymentAmount=" + percentRepaymentAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
