package org.example.haulmont.service;

import org.example.haulmont.domain.Client;
import org.example.haulmont.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    public void addClient(Client client){
        clientRepo.save(client);
    }

    public List<Client> findAll() {
        return clientRepo.findAll();
    }
}
