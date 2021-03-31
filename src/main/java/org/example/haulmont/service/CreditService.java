package org.example.haulmont.service;

import org.example.haulmont.dao.CreditDAO;
import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.Credit;
import org.example.haulmont.domain.CreditOffer;
import org.example.haulmont.repository.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepo creditRepo;

    @Autowired
    private BankService bankService;

    @Autowired
    private CreditDAO creditDAO;

    @Autowired
    private CreditOfferService offerService;

    public void addCredit(Credit credit) {

        credit.setLimit(
                new BigDecimal(credit
                        .getLimit().setScale(0, RoundingMode.DOWN)
                        .toString())
        );

        credit.setPercentRate(
                new BigDecimal(credit
                        .getPercentRate().setScale(2, RoundingMode.DOWN)
                        .toString())
        );

        creditRepo.save(credit);

        bankService.addCredit(credit);
    }

    public List<Credit> findAll() {
        return creditRepo.findAll();
    }

    public List<Credit> findByFilter(BigDecimal limitFrom, BigDecimal interestRateBefore) {
        return creditDAO.findByFilter(limitFrom, interestRateBefore);
    }

    public List<Credit> findAllByLimitFrom(BigDecimal sum) {
        return creditRepo.findAllByLimitGreaterThanEqualOrderByPercentRate(sum);
    }

    public List<Credit> filterByLimitGreaterThanTotalPayment(List<Credit> credits, BigDecimal sum, Integer amountOfMonths) {

        List<Credit> availableCredits = new ArrayList<>(credits.size());

        for (Credit c : credits) {

            BigDecimal totalPayment = ServiceUtils.getTotalPayment(sum, c.getPercentRate(), amountOfMonths);

            //если лимит кредита >= общей сумме платежа с  учетом процента
            if (c.getLimit().compareTo(totalPayment) >= 0)
                availableCredits.add(c);
        }
        return availableCredits;
    }

    public Credit findWithLowestPercent(List<Credit> credits) {
        Credit credit = new Credit();

        if (credits == null || credits.isEmpty())
            return null;

        credit.setPercentRate(new BigDecimal("9999999"));

        for (Credit c : credits) {

            //если кредитная ставка элемента коллекции ниже ставки в результирующем объекте
            //то ссылаем результирующий объект на элемент коллекции
            if (credit.getPercentRate().compareTo(c.getPercentRate()) > 0)
                credit = c;

        }

        return credit;
    }

    public void update(Credit credit, BigDecimal percentRate, BigDecimal limit) {
        if (credit == null || percentRate == null || limit == null)
            return;
        creditDAO.update(credit, percentRate, limit);
    }

    public void remove(Credit credit) {
        bankService.removeCredit(credit);

        for(CreditOffer offer : offerService.findByCredit(credit)){
            offer.setCredit(null);
        }

        creditRepo.delete(credit);
    }
}