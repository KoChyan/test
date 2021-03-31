<#import "../parts/common.ftlh" as c>
<@c.page>
    <form method="get">
        <div class="row my-3">
            <label class="col-form-label col-sm-2 my-3">Желаемая сумма</label>
            <div class="col-sm-3 my-3">
                <input type="number" step="1" min="1000" max="99999999" class="form-control" placeholder="100000"
                       name="sum">
            </div>
            <label class="col-form-label col-sm-2 my-3">Срок, месяцев</label>
            <div class="col-sm-3 my-3">
                <input type="number" step="1" min="3" max="240" class="form-control" placeholder="24"
                       name="amountOfMonths">
            </div>
            <div class="col-sm-2 my-3">
                <button class="btn btn-primary">Найти</button>
            </div>
        </div>
    </form>


    <#if !askingParams?? && !offer??>
        <h3>
            <p>Не удалось подобрать кредитное предложение. В банке нет кредитов с необходимым лимитом. Добавьте кредит в
                банк и повторите попытку</p>
            <a href="/credit/add">На странцу добавления кредита</a>
        </h3>
    </#if>

    <#if offer??>

        <div class="col">

            <div class="card text-dark bg-light m-4">

                <div class="card-header p-0">
                    <form method="post" action="/credit-offer/save">
                        <input type="hidden" value="${offer.getClient().getId()}" name="clientId">
                        <input type="hidden" value="${offer.getPaymentSchedule().getAmountOfPayments()}"
                               name="amountOfMonths">
                        <input type="hidden" value="${offer.getCreditAmount()?string[" ##"]}" name="sum">

                        <button type="submit" class="btn btn-primary mt-0 mb-3" style="width: 100%">Оформить</button>
                    </form>
                    <div class="row">
                        <div class="col text-center">
                            <p class="fw-bold">ФИО заемщика</p>
                        </div>
                        <div class="col text-center">
                            <p class="fw-bold">Телефон</p>
                        </div>
                        <div class="col text-center">
                            <p class="fw-bold">Номер паспорта</p>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col text-center">
                            <p class="card-text">
                                ${offer.getClient().getSurname()?ifExists}
                                ${offer.getClient().getName()?ifExists}
                                ${offer.getClient().getPatronymic()?ifExists}
                            </p>
                        </div>
                        <div class="col text-center">
                            <p class="card-text">${offer.getClient().getPhone()?ifExists}</p>
                        </div>
                        <div class="col text-center">
                            <p class="card-text">${offer.getClient().getPassportNumber()?ifExists}</p>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col text-center">
                            <p class="fw-bold">Сумма кредита</p>
                        </div>
                        <div class="col text-center">
                            <p class="fw-bold">Процентная ставка</p>
                        </div>
                        <div class="col text-center">
                            <p class="fw-bold">Переплата</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col text-center">
                            ${offer.getCreditAmount()?ifExists}
                        </div>
                        <div class="col text-center">
                            ${offer.getCredit().getPercentRate()?ifExists}
                        </div>
                        <div class="col text-center">
                            ${offer.getOverpayment()?ifExists}
                        </div>
                    </div>
                    <div class="row text-center mt-3">
                        <p class="fw-bold mb-0 fs-5">
                            График платежей
                        <p>
                    </div>
                    <div class="row text-center">
                        <div class="col-sm fw-bold">Дата</div>
                        <div class="col-sm fw-bold">Сумма платежа</div>
                        <div class="col-sm fw-bold">Гашение процентов</div>
                        <div class="col-sm fw-bold">Гашение осн. кредита</div>
                    </div>
                    <#list offer.getPaymentSchedule().getPayments() as payment>
                        <div class="row text-center">
                            <div class="col-sm">${payment.getDate()?ifExists}</div>
                            <div class="col-sm">${payment.getAmountOfPayment()?ifExists}</div>
                            <div class="col-sm">${payment.getInterestRepaymentAmount()?ifExists}</div>
                            <div class="col-sm">${payment.getPrincipalRepaymentAmount()?ifExists}</div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </#if>
</@c.page>