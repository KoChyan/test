package org.example.haulmont.service;

import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.Credit;
import org.example.haulmont.repository.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
