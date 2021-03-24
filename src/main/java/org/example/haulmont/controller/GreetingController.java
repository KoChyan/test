package org.example.haulmont.controller;

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

        model.addAttribute("bank", bankService.getBankFromDb());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("credits", creditService.findAll());
        return "greeting";
    }
}
