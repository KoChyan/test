package org.example.haulmont.service;

import org.example.haulmont.dao.CreditOfferDAO;
import org.example.haulmont.domain.Client;
import org.example.haulmont.domain.Credit;
import org.example.haulmont.domain.CreditOffer;
import org.example.haulmont.domain.PaymentSchedule;
import org.example.haulmont.repository.CreditOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditOfferService {

    @Autowired
    private CreditOfferRepo offerRepo;

    @Autowired
    private CreditService creditService;

    @Autowired
    private PaymentScheduleService scheduleService;

    @Autowired
    private CreditOfferDAO offerDAO;

    public List<CreditOffer> findByClient(Client client) {
        return offerRepo.findByClientId(client.getId());
    }

    public CreditOffer createOffer(BigDecimal sum, Integer amountOfMonths, Client client) {
        if (sum == null || amountOfMonths == null || client == null)
            return null;

        //все кредиты, лимит которых >= сумме, которую хочет получить клиент
        List<Credit> credits = creditService.findAllByLimitFrom(sum);

        //оставим только те, кредитный лимит которых выше чем общая сумма выплат с учетом %
        List<Credit> availableCredits = creditService.filterByLimitGreaterThanTotalPayment(credits, sum, amountOfMonths);

        //из полученных выберем тот кредит, кредитная ставка которого ниже всех
        Credit credit = creditService.findWithLowestPercent(availableCredits);

        //если подходящего кредита нет в БД
        if (credit == null)
            return null;

        CreditOffer offer = new CreditOffer();

        offer.setClient(client);
        offer.setCredit(credit);
        offer.setCreditAmount(sum);
        offer.setPaymentSchedule(scheduleService.createPaymentSchedule(amountOfMonths, credit, sum));
        offer.setOverpayment(ServiceUtils.getOverPayment(sum, credit.getPercentRate(), amountOfMonths));

        return offer;
    }

    public List<CreditOffer> findAll() {
        return offerRepo.findAll();
    }

    public void save(BigDecimal sum, Integer amountOfMonths, Client client) {
        CreditOffer offer = createOffer(sum, amountOfMonths, client);

        //добавление графика платежей в БД
        scheduleService.save(offer.getPaymentSchedule());

        //добавление кредитного предложения в БД
        offerRepo.save(offer);
    }

    public void save(CreditOffer offer) {

        //добавление графика платежей в БД
        scheduleService.save(offer.getPaymentSchedule());

        //добавление кредитного предложения в БД
        offerRepo.save(offer);
    }

    public void remove(CreditOffer offer) {

        PaymentSchedule schedule = scheduleService.findByOffer(offer);
        scheduleService.remove(schedule);

        offerRepo.delete(offer);
    }

    public List<CreditOffer> findByCredit(Credit credit) {
        return offerRepo.findByCreditId(credit.getId());
    }

    public List<CreditOffer> findByFilter(String surname, String name, String patronymic, String email, String phone, String passportNumber) {
        return offerDAO.findByFilter(surname, name, patronymic, email, phone, passportNumber);
    }
}