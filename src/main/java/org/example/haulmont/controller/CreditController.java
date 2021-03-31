package org.example.haulmont.controller;


import org.example.haulmont.domain.Credit;
import org.example.haulmont.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping()
    public String creditList(
            Model model,
            @RequestParam(name = "percentRate", required = false, defaultValue = "") BigDecimal percentRate,
            @RequestParam(name = "limit", required = false, defaultValue = "") BigDecimal limit
    ) {

        model.addAttribute("credits", creditService.findByFilter(limit, percentRate));
        return "credit/credits";
    }

    @GetMapping("/update/{id}")
    public String updateCredit(
            Model model,
            @PathVariable(name = "id") Credit credit
    ) {

        model.addAttribute("credit", credit);
        return "credit/updateCredit";
    }

    @PostMapping("/update/{id}")
    public String saveChanges(
            @PathVariable(name = "id") Credit credit,
            @Valid Credit creditFromForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            return "credit/updateCredit";

        } else {

            creditService.update(credit, creditFromForm);
            return "redirect:/credit";
        }
    }

    @GetMapping("/add")
    public String fillCreditData() {

        return "credit/addCredit";
    }

    @PostMapping("/add")
    public String addCredit(
            @Valid Credit credit,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute(credit);
            return "credit/addCredit";

        } else {

            creditService.addCredit(credit);
            return "redirect:/credit";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteCredit(@PathVariable(value = "id") Credit credit) {

        creditService.remove(credit);
        return "redirect:/credit";
    }


}
