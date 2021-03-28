package org.example.haulmont.service;

import org.example.haulmont.domain.Bank;
import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.Credit;
import org.example.haulmont.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankRepo bankRepo;


    public void addClient(Client client) {
        Bank bank = getBank();

        bank.addClient(client);

        bankRepo.save(bank);
    }

    public void addCredit(Credit credit) {
        Bank bank = getBank();

        bank.addCredit(credit);

        bankRepo.save(bank);
    }

    public Bank getBank() {
        if (bankRepo.findAll().size() < 1){
            bankRepo.save(new Bank());
        }


       return bankRepo.findAll().get(0);
    }

    public List<Bank> findAll(){
        return bankRepo.findAll();
    }
}
