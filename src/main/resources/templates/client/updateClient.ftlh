<#import "../parts/common.ftlh" as c>
<@c.page>
    <h3 class="h3 text-center my-5">
        <p class="fw-bold">
            Введите новые данные
        </p>
    </h3>

    <form method="post" action="/client/update/${client.getId()}">
        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Фамилия Имя</label>
            <div class="col-sm-3 mb-2">
                <input type="text" class="form-control" placeholder="Иванов" value="${client.getSurname()?ifExists}" name="surname"/>
            </div>

            <div class="col-sm-3 mb-2">
                <input type="text" class="form-control" placeholder="Иван" value="${client.getName()?ifExists}" name="name"/>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Отчество</label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" placeholder="Иванович" value="${client.getPatronymic()?ifExists}" name="patronymic"/>
            </div>
        </div>
        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" placeholder="example@gmail.com" value="${client.getEmail()?ifExists}" name="email"/>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Номер телефона</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="89876543210" value="${client.getPhone()?ifExists}" name="phone"/>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Номер паспорта</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="1234567890" value="${client.getPassportNumber()?ifExists}" name="passportId"/>
            </div>
        </div>

        <button type=submit" class="btn btn-primary my-5">Сохранить изменения</button>
    </form>
</@c.page>