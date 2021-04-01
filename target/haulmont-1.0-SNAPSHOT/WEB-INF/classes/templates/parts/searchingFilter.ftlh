<#macro filterByClientFields path>
    <form method="get" action="${path}">
        <p>
            <button class="btn btn-primary mt-2 mb-5" type="button" data-bs-toggle="collapse" data-bs-target="#filter"
                    aria-expanded="false" aria-controls="collapseExample">
                Фильтр
            </button>
        </p>
        <div class="collapse my-4" id="filter">
            <div class="row mb-3">
                <label class="col-form-label col-sm-2 ">Фамилия</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control " placeholder="Иванов" name="surname">
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
                    <input type="text" class="form-control" placeholder="9876543210" name="passportNumber">
                </div>
            </div>

            <button class="btn btn-primary my-4">Найти</button>
        </div>
    </form>
</#macro>