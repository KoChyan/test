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
                <input type="text" class="form-control ${(surnameError??)?string('is-invalid', '')}"
                       value="<#if client??>${client.getSurname()}</#if>" placeholder="Иванов" name="surname"/>
                <#if surnameError??>
                    <#list surnameError as surErr >
                        <div class="invalid-feedback">
                            ${surErr}
                        </div>
                    </#list>
                </#if>
            </div>

            <div class="col-sm-3 mb-2">
                <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                       value="<#if client??>${client.getName()}</#if>" placeholder="Иван" name="name"/>
                <#if nameError??>
                    <#list nameError as nErr>
                        <div class="invalid-feedback">
                            ${nErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Отчество</label>
            <div class="col-sm-6 ">
                <input type="text" class="form-control ${(patronymicError??)?string('is-invalid', '')}"
                       value="<#if client??>${client.getPatronymic()}</#if>" placeholder="Иванович" name="patronymic"/>
                <#if patronymicError??>
                    <#list patronymicError as patrErr>
                        <div class="invalid-feedback">
                            ${patrErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input type="text" class="form-control ${(emailError??)?string('is-invalid', '')}"
                       value="<#if client??>${client.getEmail()}</#if>" placeholder="example@gmail.com" name="email"/>
                <#if emailError??>
                    <#list emailError as emErr>
                        <div class="invalid-feedback">
                            ${emErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Номер телефона</label>
            <div class="col-sm-6">
                <div class="input-group">
                    <span class="input-group-text" id="basic-addon1">+7 </span>
                    <input type="text" class="form-control ${(phoneError??)?string('is-invalid', '')}"
                           value="<#if client??>${client.getPhone()}</#if>" placeholder="9876543210" name="phone"/>
                    <#if phoneError??>
                        <#list phoneError as phErr>
                            <div class="invalid-feedback">
                                ${phErr}
                            </div>
                        </#list>

                    </#if>
                </div>
            </div>
        </div>

        <div class="form-group row my-3">
            <label class="col-sm-2 col-form-label">Номер паспорта</label>
            <div class="col-sm-6">
                <input type="text" class="form-control  ${(passportNumberError??)?string('is-invalid', '')}"
                       value="<#if client??>${client.getPassportNumber()}</#if>" placeholder="123456"
                       name="passportNumber"/>
                <#if passportNumberError??>
                    <#list passportNumberError as passErr>
                        <div class="invalid-feedback">
                            ${passErr}
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <button type=submit" class="btn btn-primary my-5">Сохранить изменения</button>
    </form>
</@c.page>