<#include "base.ftl">
<#macro title>Вход</#macro>
<#macro content>

<div class="content">

    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 panel panel-danger mar-top-30">
        <div class="panel-heading">
            <h4 class="text-center">Вход</h4>
        </div>

        <div class="panel-body">
            <@sf.form action='/login/process' method="POST" modelAttribute="authForm">
                <div class="form-group">
                    <label for="">Логин</label>
                    <@sf.input path="username" type="text" class="form-control"/>
                    <@sf.errors path="username" cssClass="help-block"/>
                </div>

                <div class="form-group">
                    <label for="">Пароль</label>
                    <@sf.input path="password" type="password" class="form-control"/>
                    <@sf.errors path="username" cssClass="help-block"/>
                </div>

                <div class="form-group text-right">
                    <a href="#" data-toggle="modal" data-target="#forgot-modal" tabindex="-1">Забыли пароль?</a>
                </div>

                <p class="error" <#if error??><#else>hidden</#if>>Логин или пароль указаны с ошибкой</p>

                <div class="form-group">
                    <button type="submit" class="btn btn-block btn-primary">Войти</button>
                </div>

                <div class="form-group">
                    <p class="text-center">Нет аккаунта? <a href="/registration">Зарегистрируйтесь</a></p>
                </div>
            </@sf.form>
        </div>

    </div>

    <!-- FORGOT PASSWORD MODAL -->
    <div class="modal fade" id="forgot-modal" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header no-border">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">
                    <h4 class="text-center">Восстановление пароля</h4>
                    <br>
                    <form class="form">
                        <div class="form-group">
                            <h5>Укажите адрес электронной почты, на который вам будут высланы инструкции по
                                восстановлению
                                пароля.</h5>
                            <label for="forgot_email_input" class="sr-only">email</label>
                            <input type="email" class="form-control " id="forgot_email_input"
                                   placeholder="example@mail.ru">
                        </div>
                        <div class="form-group error" id="forgot-error">
                            <h5 class="text-center" style="display: none;">Пользователя с таким e-mail нет в нашей базе
                                данных</h5>
                        </div>
                        <div class="form-group success" style="display: none;" id="forgot-success">
                            <h5 class="text-center">На ваш e-mail отправлено письмо</h5>
                        </div>
                        <div class="btn btn-block btn-lg btn-danger" id="forgot-submit">Восстановить пароль</div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- FORGOT PASSWORD MODAL -->
</div>

</#macro>