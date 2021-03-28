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

@Service
public class CreditOfferService {

    @Autowired
    private CreditService creditService;

    @Autowired
    private PaymentScheduleService scheduleService;

    public List<CreditOffer> getPossibleByFilter(BigDecimal sum, Integer amountOfMonths) {
        if (sum == null || amountOfMonths == null)
            return new ArrayList<>();

        //все кредиты, лимит которых >= сумме, которую хочет получить клиент
        List<Credit> credits = creditService.findAllByLimitFrom(sum);

        //для кредитов, лимит которых >= общей сумме выплат с учетом %
        List<Credit> availableCredits = new ArrayList<>(credits.size());

        for (Credit c : credits) {
            BigDecimal totalPayment = ServiceUtils.getTotalPayment(sum, c.getPercentRate(), amountOfMonths);

            //если лимит кредита >= общей сумме платежа с  учетом процента
            if (c.getLimit().compareTo(totalPayment) >= 0)
                availableCredits.add(c);
        }

        //список кредитных предложений
        List<CreditOffer> offers = new ArrayList<>(amountOfMonths);
        for (Credit credit : availableCredits) {
            CreditOffer offer = new CreditOffer();

            offer.setCredit(credit);
            offer.setCreditAmount(sum);
            offer.setPaymentSchedule(scheduleService.createPaymentSchedule(amountOfMonths, credit, sum));

            offers.add(offer);
        }

        return offers;
    }
}