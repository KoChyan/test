package org.example.haulmont.service;

import org.example.haulmont.domain.Credit;
import org.example.haulmont.domain.CreditOffer;
import org.example.haulmont.domain.Payment;
import org.example.haulmont.domain.PaymentSchedule;
import org.example.haulmont.repository.PaymentScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class PaymentScheduleService {

    @Autowired
    private PaymentScheduleRepo scheduleRepo;

    @Autowired
    private PaymentService paymentService;

    public void save(PaymentSchedule paymentSchedule) {
        paymentSchedule.getPayments().forEach(p -> paymentService.save(p));

        scheduleRepo.save(paymentSchedule);
    }

    public PaymentSchedule createPaymentSchedule(Integer amountOfMonths, Credit credit, BigDecimal sum) {
        if (credit == null)
            return null;

        PaymentSchedule paymentSchedule = new PaymentSchedule();
        Payment payment;

        //дата первого платежа
        LocalDate date ;

        //количетсво платежей равно количеству месяцев
        for (int i = 0; i < amountOfMonths; i++) {
            payment = new Payment();
            date = LocalDate.now().plusWeeks(1);


            //дата платежа
            payment.setDate(java.sql.Date.valueOf(date.plusMonths(i)));

            //сумма гашения процента
            payment.setPercentRepaymentAmount(ServiceUtils
                    .getValueOfInterestRepayment(i, amountOfMonths, sum, credit.getPercentRate())
                    .setScale(2, RoundingMode.HALF_UP)
            );

            //сумма гашения тела основного кредита
            payment.setPrincipalRepaymentAmount(ServiceUtils
                    .getValueOfPrincipalRepayment(i, amountOfMonths, sum, credit.getPercentRate())
                    .setScale(2, RoundingMode.HALF_UP)
            );

            //сумма платежа
            payment.setAmountOfPayment(ServiceUtils
                    .getValueOfPayment(credit.getPercentRate(), sum, amountOfMonths)
                    .setScale(2, RoundingMode.HALF_UP)
            );

            //добавление в график платежей полученного платежа
            paymentSchedule.addPayment(payment);
        }
        return paymentSchedule;
    }

    public PaymentSchedule findByOffer(CreditOffer offer) {
        return scheduleRepo.findByCreditOfferId(offer.getId());
    }

    public void remove(PaymentSchedule schedule) {
        for (Payment payment : schedule.getPayments()) {
            paymentService.remove(payment);
        }

        scheduleRepo.delete(schedule);
    }
}