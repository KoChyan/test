package org.example.haulmont.controller;


import org.example.haulmont.domain.Credit;
import org.example.haulmont.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping()
    public String creditList(
            Model model,
            @RequestParam(name = "interestRate", required = false, defaultValue = "") BigDecimal interestRate,
            @RequestParam(name = "limit", required = false, defaultValue = "") BigDecimal limit
    ) {
        model.addAttribute("credits", creditService.findByFilter(limit, interestRate));
        return "credit/credits";
    }

    @GetMapping("/add")
    public String fillCreditData() {
        return "credit/addCredit";
    }

    @PostMapping("/add")
    public String addCredit(Credit credit) {

        creditService.addCredit(credit);
        return "redirect:/credit";
    }
}
