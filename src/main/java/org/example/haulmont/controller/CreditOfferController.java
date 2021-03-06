package org.example.haulmont.controller;

import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.CreditOffer;
import org.example.haulmont.service.CreditOfferService;
import org.example.haulmont.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/credit-offer")
public class CreditOfferController {

    @Autowired
    private CreditOfferService offerService;
    

    @GetMapping("/client/{id}/add-offer")
    public String selectParam(
            Model model,
            @RequestParam(name = "sum", required = false, defaultValue = "") BigDecimal sum,
            @RequestParam(name = "amountOfMonths", required = false, defaultValue = "") Integer amountOfMonths,
            @PathVariable(name = "id") Client client
    ) {

        model.addAttribute("offer", offerService.createOffer(sum, amountOfMonths, client));
        return "creditOffer/selectCreditOffer";
    }

    @PostMapping("/save")
    public String saveOffer(
            @RequestParam(value = "clientId") Client client,
            @RequestParam(value = "amountOfMonths") Integer amountOfMonths,
            @RequestParam(value = "sum") BigDecimal sum
    ) {

        offerService.save(sum, amountOfMonths, client);
        return "redirect:/credit-offer";
    }

    @GetMapping
    public String offerList(
            Model model,
            @RequestParam(name = "surname", required = false, defaultValue = "") String surname,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "patronymic", required = false, defaultValue = "") String patronymic,
            @RequestParam(name = "email", required = false, defaultValue = "") String email,
            @RequestParam(name = "phone", required = false, defaultValue = "") String phone,
            @RequestParam(name = "passportNumber", required = false, defaultValue = "") String passportNumber
    ) {

        model.addAttribute("offers", offerService.findByFilter(surname, name, patronymic, email, phone, passportNumber));
        return "creditOffer/creditOffers";
    }

    @PostMapping("/delete/{id}")
    public String deleteCreditOffer(@PathVariable(value = "id") CreditOffer offer) {

        offerService.remove(offer);
        return "redirect:/credit-offer";
    }

}
