package org.example.haulmont.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credit-offer")
public class CreditOfferController {

    @GetMapping()
    public String creditOfferList(Model model){

        return "creditOffers";
    }
}
