package org.example.haulmont.controller;

import org.example.haulmont.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreditController {

    @Autowired
    private CreditService creditService;


    @GetMapping("/credit")
    public String creditList(Model model){
        model.addAttribute("credits", creditService.findAll());
        return "credits";
    }

    @GetMapping("/add")
    public String fillCreditData() {
        return "addCredit";
    }
}
