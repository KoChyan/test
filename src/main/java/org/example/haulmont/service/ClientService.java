package org.example.haulmont.service;

import org.example.haulmont.dao.ClientDAO;
import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.CreditOffer;
import org.example.haulmont.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    CreditOfferService offerService;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private BankService bankService;

    @Autowired
    private ClientDAO clientDAO;


    public void addClient(Client client) {
        clientRepo.save(client);
        bankService.addClient(client);
    }

    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    public List<Client> findByFilter(String surname, String name, String patronymic, String email, String phone, String passportId) {
        return clientDAO.findByFilter(surname, name, patronymic, email, phone, passportId);
    }

    public void updateClient(Client client, String surname, String name, String patronymic, String email, String phone, String passportId) {
        if (client == null || surname == null || name == null || patronymic == null || email == null || phone == null || passportId == null)
            return;
        clientDAO.update(client, surname, name, patronymic, email, phone, passportId);
    }

    public Client findById(UUID id) {
        return clientRepo.findFirstById(id);
    }

    public void remove(Client client) {
        bankService.removeClient(client);

        for (CreditOffer offer : offerService.findByClient(client)) {
            offerService.remove(offer);
        }

        clientRepo.delete(client);
    }
}