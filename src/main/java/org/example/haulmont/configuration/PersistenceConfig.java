package org.example.haulmont.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

@Configuration
public class PersistenceConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    CriteriaBuilder getCriteriaBuilder() {
        return em.getCriteriaBuilder();
    }


}
