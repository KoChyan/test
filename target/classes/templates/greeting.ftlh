<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container m-5">
        <div class="row my-3 ">
            <div class="col-sm ">
                <h3 class="my-5">${bank.getName()?ifExists}</h3>
                <h3 class="my-5">${bank.toString()?ifExists}</h3>
                <n3></n3>
            </div>
        </div>
    </div>


</@c.page>