<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container m-5">
        <div class="row my-5 ">
            <div class="col-sm-5">
                <h1>
                    Здравствуйте!
                </h1>
            </div>
            <div class="col-sm-7 my-5">
                <h3 class="my-5">Клиентов в нашем банке: ${(bank.getCountOfClients())!'0'}</h3>
                <h3 class="my-5">Кредитов в нашем банке: ${bank.getCountOfCredits()!'0'}</h3>
            </div>
        </div>
        <div class="row my-3" >
            <h3 class="mb-3">Навигация по сайту осуществляется при помощи навигационной панели</h3>
            <div class="col-sm-6 my-3">
                <h5>
                    Для оформления кредита перейдите на страницу с клиентами и нажмите соответствующую кнопку на карте клиента
                </h5>
            </div>
        </div>
    </div>
</@c.page>