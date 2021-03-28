package org.example.haulmont.controller;

import org.example.haulmont.domain.Client;
import org.example.haulmont.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public String clientList(
            Model model,
            @RequestParam(name = "surname", required = false, defaultValue = "") String surname,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "patronymic", required = false, defaultValue = "") String patronymic,
            @RequestParam(name = "email", required = false, defaultValue = "") String email,
            @RequestParam(name = "phone", required = false, defaultValue = "") String phone,
            @RequestParam(name = "passportId", required = false, defaultValue = "") String passportId
    ) {
        Iterable<Client> clients = clientService.findByFilter(surname, name, patronymic, email, phone, passportId);
        model.addAttribute("clients", clients);
        return "client/clients";
    }

    @GetMapping("/add")
    public String fillClientData() {
        return "client/addClient";
    }

    @PostMapping("/add")
    public String addClient(Client client) {

        clientService.addClient(client);
        return "redirect:/client";
    }
}
