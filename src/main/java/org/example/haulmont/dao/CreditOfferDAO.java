package org.example.haulmont.dao;

import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.CreditOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Component
public class CreditOfferDAO {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CriteriaBuilder cb;


    public List<CreditOffer> findByFilter(String surname, String name, String patronymic, String email, String phone, String passportNumber) {
        Predicate predicate = cb.conjunction();
        CriteriaQuery<CreditOffer> criteria = cb.createQuery(CreditOffer.class);
        Root<CreditOffer> root = criteria.from(CreditOffer.class);
        Join<CreditOffer, Client> clientJoin = root.join("client");


        if (surname != null && !surname.trim().isEmpty()) {
            predicate = cb.and(cb.equal(clientJoin.get("surname"), surname));
        }
        if (name != null && !name.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(clientJoin.get("name"), name));
        }
        if (patronymic != null && !patronymic.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(clientJoin.get("patronymic"), patronymic));
        }
        if (email != null && !email.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(clientJoin.get("email"), email));
        }
        if (phone != null && !phone.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(clientJoin.get("phone"), phone));
        }
        if (passportNumber != null && !passportNumber.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(clientJoin.get("passportNumber"), passportNumber));
        }

        criteria.select(root).where(predicate);

        return em.createQuery(criteria).getResultList();
    }
}
