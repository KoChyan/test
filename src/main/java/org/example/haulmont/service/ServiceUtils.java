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
        BigDecimal multyplayer = monthPercent.add(new BigDecimal("1"))
                .setScale(24, RoundingMode.HALF_UP)
                .pow(amountOfMonths)
                .setScale(24, RoundingMode.HALF_UP);

        numerator = monthPercent.multiply(multyplayer);

        denominator = multyplayer
                .subtract(new BigDecimal("-1"))
                .setScale(24, RoundingMode.HALF_UP);


        result = sum.multiply(numerator.divide(denominator, RoundingMode.HALF_UP));

        return result;
    }

    public static BigDecimal getOverPayment(BigDecimal sum, BigDecimal percent, Integer amountOfMonths) {
        BigDecimal result;

        //payment * amountOfMonths - sumOfCredit
        result = getValueOfPayment(percent, sum, amountOfMonths)
                .multiply(new BigDecimal(amountOfMonths))
                .subtract(sum)
                .setScale(24, RoundingMode.HALF_UP);

        return result;
    }

    public static BigDecimal getTotalAmount(BigDecimal sum, BigDecimal percent, Integer amountOfMonths) {
        BigDecimal result;

        result = sum.add(getOverPayment(sum, percent, amountOfMonths))
                .setScale(24, RoundingMode.HALF_UP);

        return result;
    }

    public static BigDecimal getValueOfPrincipalRepayment(BigDecimal sum, BigDecimal percent,
                                                          Integer amountOfMonths, Integer currentMonth) {
        BigDecimal result;

        result = new BigDecimal(
                String.valueOf(
                        getValueOfPayment(percent, sum, amountOfMonths)
                                .subtract(getValueOfInterestRepayment(currentMonth, amountOfMonths, sum, percent))
                                .setScale(12, RoundingMode.HALF_UP)
                )
        );


        return result;
    }

    public static BigDecimal getValueOfInterestRepayment(Integer currentMonth, Integer amountOfMonths,
                                                         BigDecimal sum, BigDecimal percent) {

        BigDecimal result;

        result = new BigDecimal(
                String.valueOf(
                        sum.subtract(
                                getValueOfPayment(percent, sum, amountOfMonths)
                                        .multiply(new BigDecimal(String.valueOf(currentMonth)))
                        )
                                .multiply(percent)
                                .setScale(12, RoundingMode.HALF_UP)
                )
        );

        return result;
    }

    private static BigDecimal getPercentRatePerMonth(BigDecimal percent) {
        BigDecimal monthsPercent;

        monthsPercent = percent
                .multiply(new BigDecimal("12")).setScale(24, RoundingMode.HALF_UP)
                .divide(new BigDecimal("100"), RoundingMode.HALF_UP)
                .setScale(24, RoundingMode.HALF_UP);

        return monthsPercent;
    }

}
