package org.example.haulmont.service;

import org.example.haulmont.domain.Credit;
import org.example.haulmont.repository.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CreditService {

    @Autowired
    private CreditRepo creditRepo;

    @Autowired
    private BankService bankService;

    public void addCredit(Credit credit) {
        creditRepo.save(credit);
        bankService.addCredit(credit);
    }


    public List<Credit> findAll() {
        return creditRepo.findAll();
    }

    public List<Credit> findByFilter(BigDecimal interestRateTo, BigDecimal limitFrom) {
        Set<Credit> result = null;

        //если параметр interestRate не null, то ищем совпадения в БД
        //где interestRate < полученной из фильтра
        if (interestRateTo != null) {
            result = creditRepo.findAllByInterestRateBefore(interestRateTo);
        }

        //если полученное значение limitFrom не null
        if (limitFrom != null) {

            //если result null (это первое переданное поле), то ищем в бд
            //где limit > полученному из фильтра
            if(result == null){
                result = creditRepo.findAllByLimitAfter(limitFrom);

                //если result не null
            }else{

                //то пересекаем его с множеством, которое получили при поиске по лимиту
                result.retainAll(creditRepo.findAllByLimitAfter(limitFrom));
            }
        }

        //если ни одно поле фильтра не было передано, то выводим все кредиты
        if(result == null)
            return  creditRepo.findAll();

        //если result не null, то возвращаем кредитов листом
        return  new ArrayList<>(result);
    }
}
