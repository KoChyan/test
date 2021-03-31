package org.example.haulmont.controller;

import org.example.haulmont.dao.BankDAO;
import org.example.haulmont.domain.Bank;
import org.example.haulmont.service.BankService;
import org.example.haulmont.service.ClientService;
import org.example.haulmont.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @Autowired
    private BankService bankService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CreditService creditService;



    @GetMapping("/")
    public String greeting(Model model) {

        model.addAttribute("bank", bankService.getBank());
        model.addAttribute("clients", bankService.getClients(bankService.getBank().getId()));
        model.addAttribute("credits", bankService.getCredits(bankService.getBank().getId()));
        return "greeting";
    }
}
