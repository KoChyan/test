package org.example.haulmont.service;

import org.example.haulmont.dao.CreditDAO;
import org.example.haulmont.domain.Bank;
import org.example.haulmont.domain.Credit;
import org.example.haulmont.repository.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepo creditRepo;

    @Autowired
    private BankService bankService;

    @Autowired
    private CreditDAO creditDAO;


    public void addCredit(Credit credit) {

        credit.setLimit(
                new BigDecimal(credit
                        .getLimit().setScale(12, RoundingMode.HALF_UP)
                        .toString())
        );

        credit.setInterestRate(
                new BigDecimal(credit
                        .getInterestRate().setScale(6, RoundingMode.HALF_UP)
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
        return creditRepo.findAllByLimitGreaterThanEqual(sum);
    }

    public boolean ifExists(Credit credit){
        return creditDAO.ifExists(credit, bankService.getBank());
    }
}
