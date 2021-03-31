<#import "../parts/common.ftlh" as c>
<#import "../parts/searchingFilter.ftlh" as f>
<@c.page>

    <@f.filterByClientFields "/credit-offer" />

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#list offers as offer>
            <div class="col my-4">
                <div class="card text-dark bg-light my-4">
                    <div class="card-header">
                        <div class="row my-2">
                            <div class="col-sm-4 text-center">
                                <p class="fw-bold">ФИО заемщика</p>
                            </div>
                            <div class="col text-center">
                                <p class="card-text">
                                    ${offer.getClient().getSurname()}
                                    ${offer.getClient().getName()}
                                    ${offer.getClient().getPatronymic()}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row my-2">
                            <div class="col-sm-4">
                                <p class="fw-bold">Сумма кредита</p>
                            </div>
                            <div class="col text-center">
                                <p class="card-text">${offer.getCreditAmount()}</p>
                            </div>
                        </div>
                        <div class="row my-2">
                            <div class="col-sm-4">
                                <p class="fw-bold">Количество платежей</p>
                            </div>
                            <div class="col text-center">
                                <p class="card-text">${offer.getPaymentSchedule().getAmountOfPayments()}</p>
                            </div>
                        </div>
                        <div class="row my-2">
                            <div class="col-sm-4">
                                <p class="fw-bold">Переплата</p>
                            </div>
                            <div class="col text-center">
                                <p class="card-text">${offer.getOverpayment()}</p>
                            </div>
                        </div>
                    </div>
                    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                        <div class="btn-group" role="group" style="width: 100%">
                            <button type="button" class="btn btn-primary"
                                    data-bs-toggle="collapse"
                                    data-bs-target="#paymentSchedule${offer.getPaymentSchedule().getId()}"
                                    aria-expanded="false" aria-controls="collapseExample">
                                Показать график платежей
                            </button>
                            <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                Редактировать
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1" ">
                                <li>
                                    <form method="post" action="/credit-offer/delete/${offer.getId()}">
                                        <button class="dropdown-item" type="submit">Удалить</button>
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="collapse my-4" id="paymentSchedule${offer.getPaymentSchedule().getId()}">
                        <div class="row text-center">
                            <div class="col-sm fw-bold">Дата</div>
                            <div class="col-sm fw-bold">Сумма платежа</div>
                        </div>
                        <#list offer.getPaymentSchedule().getPayments() as payment>
                            <div class="row text-center">
                                <div class="col-sm">${payment.getDate()?date}</div>
                                <div class="col-sm">${payment.getAmountOfPayment()}</div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</@c.page>