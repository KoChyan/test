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
            <div class="col-sm-5">
                <input type="number" step="1" min="3" max="240" class="form-control" placeholder="24"
                       name="time">
            </div>
        </div>

        <button class="btn btn-primary my-4">Найти</button>
        </div>
    </form>

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#list creditOffers as offer>
            <div class="col">
                <div class="card text-dark bg-light my-4" style="max-width: 48rem">
                    <div class="card-body">
                        <p class="card-text m-2">
                        <div class="row">
                            <div class="col-sm-4">offer</div>
                            <div class="col-sm">${offer.getPaymentSchedule().getPayments().toString()}</div>
                        </div>
                        </p>
                    </div>
                </div>
            </div>
        </#list>
    </div>


</@c.page>