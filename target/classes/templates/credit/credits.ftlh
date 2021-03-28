<#import "../parts/common.ftlh" as c>
<@c.page>
    <form method="get" action="/credit">
        <p>
            <button class="btn btn-primary my-3" type="button" data-bs-toggle="collapse" data-bs-target="#filter"
                    aria-expanded="false" aria-controls="collapseExample">
                Фильтр
            </button>
        </p>
        <div class="collapse my-4" id="filter">
            <div class="row mb-3">
                <label class="col-form-label col-sm-4">Процентная ставка, до</label>
                <div class="col-sm-6">
                    <input type="number" step="0.001" min="0" max="100" class="form-control" placeholder="21.3"
                           name="interestRate"/>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-form-label col-sm-4">Лимит по кредиту, от</label>
                <div class="col-sm-6">
                    <input type="number" step="1" min="1000" max="99999999" class="form-control" placeholder="10000"
                           name="limit"/>
                </div>
            </div>

            <button class="btn btn-primary my-4">Найти</button>
        </div>
    </form>

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#list credits as credit>
            <div class="col">
                <div class="card text-dark bg-light my-4" style="max-width: 48rem">
                    <div class="card-body">
                        <p class="card-text m-2">
                        <div class="row my-2">
                            <div class="col-sm-4">Процентная ставка</div>
                            <div class="col-sm">
                                ${credit.getInterestRate()?ifExists}
                            </div>
                        </div>
                        <div class="row my-2">
                            <div class="col-sm-4">Лимит по кредиту</div>
                            <div class="col-sm">
                                ${credit.getLimit()?ifExists}
                            </div>
                        </div>
                        </p>
                    </div>
                </div>
            </div>
        </#list>

    </div>
</@c.page>