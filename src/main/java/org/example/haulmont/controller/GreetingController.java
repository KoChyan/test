package org.example.haulmont.controller;

import org.example.haulmont.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @Autowired
    private BankService bankService;


    @GetMapping("/")
    public String greeting(Model model) {

        model.addAttribute("bank", bankService.getBank());
        return "greeting";
    }
}
