package org.example.haulmont.service;

import org.example.haulmont.domain.Credit;
import org.example.haulmont.domain.Payment;
import org.example.haulmont.domain.PaymentSchedule;
import org.example.haulmont.repository.PaymentScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PaymentScheduleService {

    @Autowired
    private PaymentScheduleRepo scheduleRepo;

    public void save(PaymentSchedule paymentSchedule) {
        scheduleRepo.save(paymentSchedule);
    }

    public PaymentSchedule createPaymentSchedule(Integer amountOfMonths, Credit credit, BigDecimal sum) {

        PaymentSchedule paymentSchedule = new PaymentSchedule();
        Payment payment;

        //дата первого платежа через неделю
        LocalDate date = LocalDate.now().plusWeeks(1);

        //количетсво платежей равно количеству месяцев
        for (int i = 0; i < amountOfMonths; i++) {
            payment = new Payment();

            //дата платежа
            payment.setDate(java.sql.Date.valueOf(date.plusMonths(i)));

            //сумма гашения процента
            payment.setInterestRepaymentAmount(
                    ServiceUtils.getValueOfInterestRepayment(i, amountOfMonths, sum, credit.getPercentRate())
            );

            //сумма гашения тела основного кредита
            payment.setPrincipalRepaymentAmount(
                    ServiceUtils.getValueOfPrincipalRepayment(i, amountOfMonths, sum, credit.getPercentRate())
            );

            //сумма платежа
            payment.setAmountOfPayment(
                    ServiceUtils.getValueOfPayment(credit.getPercentRate(), sum, amountOfMonths)
            );

            //связь от платежа к графику платежей
            payment.setPaymentSchedule(paymentSchedule);

            //добавление в график платежей полученного платежа
            paymentSchedule.addPayment(payment);
        }
        return paymentSchedule;
    }

}
