<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container m-5">
        <div class="row my-3 ">
            <div class="col-sm ">
                <h3 class="my-5">${bank.getName()!}</h3>
                <#list clients as client>
                    <h3 class="my-5">${client.getName()}</h3>
                </#list>
            </div>
        </div>
    </div>
</@c.page>