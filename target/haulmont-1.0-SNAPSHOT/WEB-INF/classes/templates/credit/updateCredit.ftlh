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
                <input type="number" step="0.01" class="form-control ${(percentRateError??)?string('is-invalid', '')}"
                       placeholder="11.3" name="percentRate"/>
                <#if percentRateError??>
                    <#list percentRateError as prErr >
                        <div class="invalid-feedback">
                            ${prErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-3 col-form-label">Лимит по кредиту</label>
            <div class="col-sm-6 ">
                <input type="number" step="1" class="form-control ${(limitError??)?string('is-invalid', '')}"
                       placeholder="10000" name="limit"/>
                <#if limitError??>
                    <#list limitError as lErr >
                        <div class="invalid-feedback">
                            ${lErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <button type=submit" class="btn btn-primary my-5">Применить изменения</button>
    </form>
</@c.page>