package org.example.haulmont.service;

import org.example.haulmont.dao.BankDAO;
import org.example.haulmont.domain.Bank;
import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.Credit;
import org.example.haulmont.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private BankDAO bankDAO;

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

    public List<Credit> getCredits(UUID id){
       return bankDAO.getCredits(id);
    }

    public List<Client> getClients(UUID id){
        return bankDAO.getClients(id);
    }

    public void removeClient(Client client) {
        bankDAO.remove(client);
    }

    public void removeCredit(Credit credit) {
        bankDAO.remove(credit);
    }

}