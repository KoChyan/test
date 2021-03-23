<#import "parts/common.ftlh" as c>
<@c.page>
    <form method="post" action="/client/add">
        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Фамилия Имя</label>
            <div class="col-sm-3 mb-2">
                <input type="text" class="form-control" placeholder="Петров" name="surname"/>
            </div>

            <div class="col-sm-3 mb-2">
                <input type="text" class="form-control" placeholder="Петр" name="name"/>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Отчество</label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control" placeholder="Петрович" name="patronymic"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input type="email" class="form-control" placeholder="example@gmail.com" name="email"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Номер телефона</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="89876543210" name="phone"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Номер паспорта</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="1234 567890" name="passportId"/>
            </div>
        </div>

        <button type=submit" class="btn btn-primary my-5">Добавить</button>
    </form>
</@c.page>