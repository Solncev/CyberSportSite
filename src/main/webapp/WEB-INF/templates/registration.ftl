<#include "base.ftl">
<#macro title>Регистрация</#macro>
<#macro extrahead>
</#macro>
<#macro content>


<div class="content">

    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-danger mar-top-30">
        <div class="panel-heading">
            <h4 class="text-center">Регистрация</h4>
        </div>
        <div class="panel-body">

            <@sf.form action="/registration" method="post" modelAttribute="userForm">
                <div class="form-group">
                    <label for="">Никнейм</label>
                    <@sf.input path="username" type="text" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="">Email</label>
                    <@sf.input path="email" type="email" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="">Пароль</label>
                    <@sf.input path="password" type="password" class="form-control" name="pass"/>
                </div>

                <#if error??>
                <p class="error">Пользователь с таким <#if error == "email_error">email<#elseif error == "username_error">никнейном</#if> уже существует</p>
                </#if>

                <div class="form-group">
                    <button type="submit" class="btn btn-block btn-primary">Зарегистрироваться</button>
                </div>

                <div class="form-group">
                    <p class="text-center">Уже зарегистрированы? <a href="/login">Войдите</a></p>
                </div>
            </@sf.form>
        </div>
    </div>
</div>


</#macro>