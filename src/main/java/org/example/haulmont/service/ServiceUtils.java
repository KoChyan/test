package org.example.haulmont.service;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class ServiceUtils {

    static BigDecimal getValueOfPayment(BigDecimal percent, BigDecimal sum, Integer amountOfMonths) {
        BigDecimal result;

        BigDecimal monthPercent = getPercentRatePerMonth(percent);
        sum = sum.setScale(24, RoundingMode.HALF_UP);

        //числитель, знаменатель
        BigDecimal numerator;
        BigDecimal denominator;

        // (1+percent)^amountOfMonths
        BigDecimal multiplayer = monthPercent.add(new BigDecimal("1"))
                .pow(amountOfMonths)
                .setScale(24, RoundingMode.HALF_UP);

        numerator = monthPercent.multiply(multiplayer)
                .setScale(48, RoundingMode.HALF_UP);

        denominator = multiplayer.subtract(new BigDecimal("1"))
                .setScale(24, RoundingMode.HALF_UP);

        result = sum.multiply(numerator)
                .divide(denominator, RoundingMode.HALF_UP);

        return result;
    }

    public static BigDecimal getOverPayment(BigDecimal sum, BigDecimal percent, Integer amountOfMonths) {
        BigDecimal result;

        //payment * amountOfMonths - sumOfCredit
        result = getValueOfPayment(percent, sum, amountOfMonths)
                .multiply(new BigDecimal(amountOfMonths.toString()))
                .subtract(sum)
                .setScale(24, RoundingMode.HALF_UP);

        return result;
    }

    public static BigDecimal getTotalPayment(BigDecimal sum, BigDecimal percent, Integer amountOfMonths) {
        BigDecimal result;

        result = sum.add(getOverPayment(sum, percent, amountOfMonths))
                .setScale(24, RoundingMode.HALF_UP);

        return result;
    }

    public static BigDecimal getValueOfPrincipalRepayment(Integer currentMonth, Integer amountOfMonths, BigDecimal sum, BigDecimal percent) {
        BigDecimal result;

        // сумма, которая ушла на погашение тела платежа процента в этом месяце
        BigDecimal percentPayment = getValueOfInterestRepayment(currentMonth, amountOfMonths, sum, percent).setScale(24, RoundingMode.HALF_UP);

        //размер платежа
        BigDecimal valueOfPayment = getValueOfPayment(percent, sum, amountOfMonths);

        result = valueOfPayment.subtract(percentPayment).setScale(24, RoundingMode.HALF_UP);

        return result;
    }

    public static BigDecimal getValueOfInterestRepayment(Integer currentMonth, Integer amountOfMonths, BigDecimal sum, BigDecimal percent) {

        BigDecimal result;

        // уже оплачено
        // payment * month number
        BigDecimal alreadyPaid = getValueOfPayment(getPercentRatePerMonth(percent), sum, amountOfMonths)
                .multiply(new BigDecimal(currentMonth)).setScale(24, RoundingMode.HALF_UP);

        // (sum - already paid) * month percent
        result = sum.subtract(alreadyPaid).multiply(getPercentRatePerMonth(percent)).setScale(24, RoundingMode.HALF_UP);

        return result;
    }

    public static BigDecimal getPercentRatePerMonth(BigDecimal percent) {
        BigDecimal monthsPercent;

        percent = percent.setScale(24, RoundingMode.HALF_UP);

        monthsPercent = percent
                .divide(new BigDecimal("12"), RoundingMode.HALF_UP)
                .divide(new BigDecimal("100"), RoundingMode.HALF_UP)
                .setScale(24, RoundingMode.HALF_UP);

        return monthsPercent;
    }

}
