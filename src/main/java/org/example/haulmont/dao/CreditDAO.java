package org.example.haulmont.dao;


import org.example.haulmont.domain.Bank;
import org.example.haulmont.domain.Credit;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class CreditDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    public List<Credit> findByFilter(BigDecimal limit, BigDecimal interestRate) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Credit> criteria = cb.createQuery(Credit.class);
        Root<Credit> root = criteria.from(Credit.class);

        Predicate predicate = cb.conjunction();

        if(limit != null) {
            predicate = cb.and(cb.ge(root.get("limit"), limit));
        }
        if(interestRate != null){
            predicate.getExpressions().add(cb.le(root.get("interestRate"), interestRate));
        }

        criteria.where(predicate);

        return em.createQuery(criteria).getResultList();
    }


    public boolean ifExists(Credit credit, Bank bank){

        if(credit == null || bank == null)
            return false;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Credit> criteria = cb.createQuery(Credit.class);
        Root<Credit> root = criteria.from(Credit.class);

        criteria.where(cb.equal(root.get("Bank"), bank));

        System.out.println(em.createQuery(criteria).getResultList());
        //если не найдено кредита с таким банком
       if(em.createQuery(criteria).getResultList().isEmpty())
           return false;

       return true;
    }
}
