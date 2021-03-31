<#import "../parts/common.ftlh" as c>
<#import "../parts/searchingFilter.ftlh" as f>
<@c.page>

    <@f.filterByClientFields "/client" />

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#list clients as client>
            <div class="col">
                <div class="card text-dark bg-light my-4" style="max-width: 48rem">
                    <h5 class="card-title m-3">
                        <div class="row">
                            <div class="col-sm-4">ФИО</div>
                            <div class="col-sm">
                                ${client.getSurname()?ifExists}
                                ${client.getName()?ifExists}
                                ${client.getPatronymic()?ifExists}
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
                            <div class="col-sm">${client.getPassportNumber()?ifExists}</div>
                        </div>
                        </p>
                    </div>
                    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                        <a href="/credit-offer/client/${client.getId()}/add-offer" class="btn btn-primary"
                           role="button">Оформить кредит</a>
                        <div class="btn-group" role="group">
                            <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                Редактировать
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                <li>
                                    <a class="dropdown-item" href="/client/update/${client.getId()}">
                                        Изменить информацию
                                    </a>
                                </li>
                                <li>
                                    <form method="post" action="/client/delete/${client.getId()}">
                                        <button class="dropdown-item" type="submit">Удалить</button>
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
