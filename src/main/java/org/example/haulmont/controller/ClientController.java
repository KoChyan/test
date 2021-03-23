package org.example.haulmont.controller;

import org.example.haulmont.domain.Client;
import org.example.haulmont.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public String clientList(Model model){

        model.addAttribute("clients", clientService.findAll());

        return "clients";
    }

    @GetMapping("/add")
    public String fillClientData(){
        return "addClient";
    }

    @PostMapping("/add")
    public String addClient(Client client){

        clientService.addClient(client);
        return "redirect:/client";
    }
}
