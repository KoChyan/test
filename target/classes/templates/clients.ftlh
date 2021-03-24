<#import "parts/common.ftlh" as c>
<@c.page>
    <form method="get" action="/client">
        <p>
            <button class="btn btn-primary my-3" type="button" data-bs-toggle="collapse" data-bs-target="#filter"
                    aria-expanded="false" aria-controls="collapseExample">
                Фильтр
            </button>
        </p>
        <div class="collapse my-4" id="filter">
            <div class="row mb-3">
                <label class="col-form-label col-sm-2">Фамилия</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="Иванов" name="surname">
                </div>
                <label class="col-form-label col-sm-2">Email</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" placeholder="example@gmail.com" name="email">
                </div>
            </div>

            <div class="row my-3">
                <label class="col-form-label col-sm-2">Имя</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="Иван" name="name">
                </div>
                <label class="col-form-label col-sm-2">Номер телефона</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" placeholder="89876543210" name="phone">
                </div>
            </div>
            <div class="row my-3">
                <label class="col-form-label col-sm-2">Отчество</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="Иванович" name="patronymic">
                </div>
                <label class="col-form-label col-sm-2">Номер паспорта</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" placeholder="9876543210" name="passportId">
                </div>
            </div>

            <button class="btn btn-primary my-4">Найти</button>
        </div>
    </form>

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#if clients??>
            <#list clients as client>
                <div class="col">
                    <div class="card text-dark bg-light my-4" style="max-width: 48rem">
                        <h5 class="card-title m-3">
                            <div class="row">
                                <div class="col-sm-4">ФИО</div>
                                <div class="col-sm">
                                    ${client.getSurname()?ifExists} ${client.getName()?ifExists} ${client.getPatronymic()?ifExists}
                                </div>
                            </div>
                        </h5>
                        <div class="card-body">
                            <p class="card-text m-2">
                            <div class="row">
                                <div class="col-sm-4">Email</div>
                                <div class="col-sm">${client.getEmail()?ifExists}</div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">Номер телефона</div>
                                <div class="col-sm">${client.getPhone()?ifExists}</div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">Номер паспорта</div>
                                <div class="col-sm">${client.getPassportId()?ifExists}</div>
                            </div>
                            </p>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
        </#if>

    </div>
</@c.page>
