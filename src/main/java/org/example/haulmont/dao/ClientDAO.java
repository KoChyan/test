package org.example.haulmont.dao;

import org.example.haulmont.domain.Bank;
import org.example.haulmont.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class ClientDAO {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CriteriaBuilder cb;


    @Transactional
    public List<Client> findByFilter(String surname, String name, String patronymic, String email, String phone, String passportId) {
        Predicate predicate = cb.conjunction();
        CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
        Root<Client> root = criteria.from(Client.class);

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

    @Transactional
    public void update(Client client, String surname, String name, String patronymic, String email, String phone, String passportNumber) {

        String stringQuery = "UPDATE Client as c SET " +
                "c.surname = :surname, c.name = :name, c.patronymic = :patronymic, " +
                "c.email = :email, c.phone = :phone, c.passportNumber = :passportNumber " +
                "WHERE c.id = :id";

        em.createQuery(stringQuery)
                .setParameter("surname", surname)
                .setParameter("name", name)
                .setParameter("patronymic", patronymic)
                .setParameter("email", email)
                .setParameter("phone", phone)
                .setParameter("passportNumber", passportNumber)
                .setParameter("id", client.getId())
                .executeUpdate();
    }

}
