<#import "../parts/common.ftlh" as c>
<@c.page>
    <form method="get" action="/credit">
        <div>
            <button class="btn btn-primary mt-2 mb-5" type="button" data-bs-toggle="collapse" data-bs-target="#filter"
                    aria-expanded="false" aria-controls="collapseExample">
                Фильтр
            </button>
        </div>
        <div class="collapse my-4" id="filter">
            <div class="row mb-3">
                <label class="col-form-label col-sm-4">Процентная ставка, до</label>
                <div class="col-sm-6">
                    <input type="number" step="0.01" min="0" max="99.9" class="form-control" placeholder="21.3"
                           name="percentRate"/>
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
                        <div class="row my-2">
                            <div class="col-sm-4">Процентная ставка</div>
                            <div class="col-sm">
                                ${credit.getPercentRate()?ifExists}
                            </div>
                        </div>
                        <div class="row my-2">
                            <div class="col-sm-4">Лимит по кредиту</div>
                            <div class="col-sm">
                                ${credit.getLimit()?ifExists}
                            </div>
                        </div>
                    </div>
                    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                        <div class="btn-group" role="group" style="width: 100%">
                            <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                Редактировать
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1" style="width: 100%">
                                <li><a class="dropdown-item text-center" href="/credit/update/${credit.getId()}">
                                        Изменить информацию</a>
                                </li>
                                <li>
                                    <form method="post" action="/credit/delete/${credit.getId()}">
                                        <button class="dropdown-item  text-center" type="submit">Удалить</button>
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </#list>

    </div>
</@c.page>