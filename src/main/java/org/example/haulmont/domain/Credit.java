package org.example.haulmont.domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(name = "limit")
    private BigDecimal limit;

    @Column(name = "interestRate")
    private BigDecimal interestRate;


    public Credit() {
    }

    public Credit(BigDecimal interestRate, BigDecimal limit) {
        this.limit = limit;
        this.interestRate = interestRate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "limit=" + limit +
                ", interestRate=" + interestRate +
                '}';
    }
}
