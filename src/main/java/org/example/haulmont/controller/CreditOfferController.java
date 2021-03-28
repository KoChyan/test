package org.example.haulmont.controller;

import com.fasterxml.jackson.core.JsonToken;
import org.example.haulmont.service.CreditOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/credit-offer")
public class CreditOfferController {

    @Autowired
    private CreditOfferService offerService;

    @GetMapping("/select")
    public String selectParam(
            Model model,
            @RequestParam(name = "sum", required = false, defaultValue = "") BigDecimal sum,
            @RequestParam(name = "amountOfMonths", required = false, defaultValue = "") Integer amountOfMonths
            ){
        model.addAttribute("creditOffers", offerService.getPossibleByFilter(sum, amountOfMonths));
        return "creditOffer/selectCreditOffer";
    }

}
