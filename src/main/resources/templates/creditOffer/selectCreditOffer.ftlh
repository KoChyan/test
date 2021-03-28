<#import "../parts/common.ftlh" as c>
<@c.page>
    <form method="get" action="/credit-offer/select">
        <div class="row my-3">
            <label class="col-form-label col-sm-2">Желаемая сумма</label>
            <div class="col-sm-3">
                <input type="number" step="1" min="1000" max="99999999" class="form-control" placeholder="100000"
                       name="sum">
            </div>
            <label class="col-form-label col-sm-2">Срок, месяцев</label>
            <div class="col-sm-3">
                <input type="number" step="1" min="3" max="240" class="form-control" placeholder="24"
                       name="amountOfMonths">
            </div>

        </div>
        <button class="btn btn-primary my-3">Найти</button>
    </form>

    <div class="row row-cols-1 row-cols-md-1 g-4">
    <#list creditOffers as offer>
    <div class="col">
        <div class="card text-dark bg-light m-4">
            <div class="card-header">
                <div class="row">
                    <div class="col>" style="width: 50%">
                        <p class="fw-bold">Сумма кредита</p>
                    </div>
                    <div class="col" style="width: 50%">
                        <p class="fw-bold">Процентная ставка</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col" style="width: 50%">
                        ${offer.getCreditAmount()}
                    </div>
                    <div class="col" style="width: 50%">
                        ${offer.getCredit().getPercentRate()}
                    </div>
                </div>
            </div>
            <div class="card-body">
                <p class="card-text m-2">

                <div class="card-title">
                    <p class="fw-bold fs-5 text-center my-3">
                        График платежей
                    </p>
                </div>
                <div class="row mt-2">
                    <div class="col>" style="width: 20%">
                        <p class="fw-bold">Дата</p>
                    </div>
                    <div class="col>" style="width: 30%">
                        <p class="fw-bold">Сумма платежа</p>
                    </div>
                    <div class="col>" style="width: 25%">
                        <p class="fw-bold">На осн. долг</p>
                    </div>
                    <div class="col>" style="width: 25%">
                        <p class="fw-bold">На проценты</p>
                    </div>
                </div>
                <div class="row mt-2">
                    <#list offer.getPaymentSchedule().getPayments() as p>
                        <div class="col>" style="width: 20%">
                            ${p.getDate()}
                        </div>
                        <div class="col>" style="width: 30%">
                            ${p.getAmountOfPayment()}
                        </div>
                        <div class="col>" style="width: 25%">
                            ${p.getPrincipalRepaymentAmount()}
                        </div>
                        <div class="col>" style="width: 25%">
                            ${p.getInterestRepaymentAmount()}
                        </div>
                    </#list>
                    </p>
                </div>
            </div>
        </div>
        </#list>
    </div>

</@c.page>