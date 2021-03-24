package org.example.haulmont.service;

import org.example.haulmont.domain.Bank;
import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.Credit;
import org.example.haulmont.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepo bankRepo;


    public void addClient(Client client) {
        Bank bank = getBankFromDb();
        bank.addClient(client);

        bankRepo.save(bank);
    }

    public void addCredit(Credit credit) {
        Bank bank = getBankFromDb();
        bank.addCredit(credit);

        bankRepo.save(bank);
    }

    public Bank getBankFromDb() {
        //если банк не создан
        if (bankRepo.findAll().size() == 0)
            bankRepo.save(new Bank());

        return bankRepo.findAll().get(0);
    }
}
