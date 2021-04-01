package org.example.haulmont.domain;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "CREDIT")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @NotNull(message = "Обязательное поле")
    @DecimalMin(value = "1000", message = "Минимум 1000")
    @DecimalMax(value = "99999999", message = "Максимум 99999999")
    @Digits(integer = 8, fraction = 0, message = "Недопустимое значение")
    @Column(name = "limit")
    private BigDecimal limit;

    @NotNull(message = "Обязательное поле")
    @DecimalMin(value = "0.1", message = "Минимум 0.1")
    @DecimalMax(value = "99.9", message = "Максимум 99.9")
    @Digits(integer = 2, fraction = 2, message = "Недопустимое значение")
    @Column(name = "percent_rate")
    private BigDecimal percentRate;

    public Credit() {
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

    public BigDecimal getPercentRate() {
        return percentRate;
    }

    public void setPercentRate(BigDecimal percentRate) {
        this.percentRate = percentRate;
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
                ", percentRate=" + percentRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return id.equals(credit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
