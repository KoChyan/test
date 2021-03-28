<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container m-5">
        <div class="row my-3 ">
            <div class="col-sm ">

                <#list banks as b>
                    <h3 class="my-5">${b?ifExists}</h3>
                </#list>
                <n3></n3>
            </div>
        </div>
    </div>


</@c.page>