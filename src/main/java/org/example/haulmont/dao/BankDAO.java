package org.example.haulmont.dao;

import org.example.haulmont.domain.Bank;
import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.Credit;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Component
public class BankDAO {

    @PersistenceContext
    private EntityManager em;


    public List<Credit> getCredits(UUID id) {
        Bank bank = em.find(Bank.class, id);

        return bank.getCredits();
    }

    public List<Client> getClients(UUID id) {
        Bank bank = em.find(Bank.class, id);

        return bank.getClients();
    }

    public void remove(Client client) {
        Bank bank = em.find(Bank.class, client.getBank().getId());
        bank.removeClient(client);
    }

    public void remove(Credit credit) {
        Bank bank = em.find(Bank.class, credit.getBank().getId());
        bank.removeCredit(credit);
    }
}