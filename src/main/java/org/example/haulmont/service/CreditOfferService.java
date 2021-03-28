package org.example.haulmont.service;

import org.example.haulmont.domain.Credit;
import org.example.haulmont.domain.CreditOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditOfferService {

    @Autowired
    private CreditService creditService;

    @Autowired
    private PaymentScheduleService scheduleService;

    public List<CreditOffer> createByFilter(BigDecimal sum, Integer amountOfMonths) {
        List<Credit> credits;

        //все кредиты, кредитный лимит которых >= сумме, которую хочет получить клиент
        credits = creditService.findAllByLimitFrom(sum);

        //кредиты, лимит которых выше, чем общая сумма выплат с учетом %
        List<Credit> availableCredits = credits.stream()
                .filter(c -> creditService.ifExists(c))
                .collect(Collectors.toList());

        //список кредитных предложений
        Set<CreditOffer> offers = new HashSet<>(amountOfMonths);


        for (Credit credit : availableCredits) {

            CreditOffer offer = new CreditOffer();

            offer.setCredit(credit);
            offer.setCreditAmount(sum);
            offer.setPaymentSchedule(scheduleService.createPaymentSchedule(amountOfMonths, credit, sum));

            offers.add(offer);
        }
        System.out.println(offers);
        return new ArrayList<>(offers);
    }

}

