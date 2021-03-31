package org.example.haulmont.controller;

import org.example.haulmont.domain.Client;
import org.example.haulmont.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
            @RequestParam(name = "passportNumber", required = false, defaultValue = "") String passportNumber
    ) {
        List<Client> clients = clientService.findByFilter(surname, name, patronymic, email, phone, passportNumber);

        model.addAttribute("clients", clients);
        return "client/clients";
    }

    @GetMapping("/update/{id}")
    public String updateProfile(
            Model model,
            @PathVariable(name = "id") Client client
    ) {
        model.addAttribute("client", clientService.findById(client.getId()));

        return "client/updateClient";
    }

    @PostMapping("/update/{id}")
    public String saveChanges(
            @PathVariable(value = "id") Client client,
            @Valid Client clientFromForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("client", client);
            return "client/addClient";

        } else {

            clientService.updateClient(client, clientFromForm);
            return "redirect:/client";
        }
    }


    @PostMapping("/delete/{id}")
    public String deleteClient(@PathVariable(value = "id") Client client) {

        clientService.remove(client);
        return "redirect:/client";
    }


    @GetMapping("/add")
    public String fillClientData() {
        return "client/addClient";
    }

    @PostMapping("/add")
    public String addClient(
            @Valid Client client,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("client", client);
            return "client/addClient";

        } else {

            clientService.addClient(client);
            return "redirect:/client";
        }
    }
}
