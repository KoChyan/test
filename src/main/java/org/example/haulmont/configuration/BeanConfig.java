package org.example.haulmont.configuration;

import org.example.haulmont.domain.Bank;
import org.example.haulmont.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private BankService bankService;

    @Bean
    public Bank getBank(){
        return bankService.getBank();
    }

}