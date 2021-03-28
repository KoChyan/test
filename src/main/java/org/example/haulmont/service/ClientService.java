package org.example.haulmont.service;

import org.example.haulmont.dao.ClientDAO;
import org.example.haulmont.domain.Client;
import org.example.haulmont.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

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
}
