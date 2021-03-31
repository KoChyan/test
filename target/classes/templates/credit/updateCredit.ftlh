<#import "../parts/common.ftlh" as c>
<@c.page>
    <h3 class="h3 text-center my-5">
        <p class="fw-bold">
            Введите новые данные
        </p>
    </h3>

    <form method="post" action="/credit/update/${credit.getId()}">
        <div class="form-group row my-3">
            <label class="col-sm-3 col-form-label">Процентная ставка</label>
            <div class="col-sm-6 mb-2">
                <input type="number" step="0.01" min="0.01" max="100" class="form-control" placeholder="11.3"
                        name="percentRate"/>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-3 col-form-label">Лимит по кредиту</label>
            <div class="col-sm-6 ">
                <input type="number" step="1" min="1000" max="99999999" class="form-control" placeholder="10000"
                    name="limit"/>
            </div>
        </div>

        <button type=submit" class="btn btn-primary my-5">Применить изменения</button>
    </form>
</@c.page>