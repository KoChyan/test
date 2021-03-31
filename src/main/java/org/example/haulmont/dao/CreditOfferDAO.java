package org.example.haulmont.dao;

import org.example.haulmont.domain.CreditOffer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class CreditOfferDAO {

    @PersistenceContext
    private EntityManager em;

    public List<CreditOffer> findByFilter(String surname, String name, String patronymic, String email, String phone, String passportId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CreditOffer> criteria = cb.createQuery(CreditOffer.class);
        Root<CreditOffer> root = criteria.from(CreditOffer.class);

        Predicate predicate = cb.conjunction();

        if (surname != null && !surname.trim().isEmpty()) {
            predicate = cb.and(cb.equal(root.get("surname"), surname));
        }
        if (name != null && !name.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(root.get("name"), name));
        }
        if (patronymic != null && !patronymic.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(root.get("patronymic"), patronymic));
        }
        if (email != null && !email.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(root.get("email"), email));
        }
        if (phone != null && !phone.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(root.get("phone"), phone));
        }
        if (passportId != null && !passportId.trim().isEmpty()) {
            predicate.getExpressions().add(cb.equal(root.get("passportNumber"), passportId));
        }

        criteria.where(predicate);

        return em.createQuery(criteria).getResultList();

    }
}
