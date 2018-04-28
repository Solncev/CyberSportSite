<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><@title/></title>
    <!-- core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">

    <link href="/css/main.css" rel="stylesheet">
    <link href="/css/profile.css" rel="stylesheet">


</head>
<!--/head-->

<body id="home" class="homepage">

<header id="header">
    <nav id="main-menu" class="navbar navbar-default navbar-fixed-top top-nav-collapse" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/profile">
                    <img src="/images/logo.png" style="width: 170px;" alt="logo">
                </a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="scroll <#if springMacroRequestContext.requestUri?starts_with("/tournaments")>active</#if>">
                        <a href="/tournaments">Турниры </a>
                    </li>
                    <li class="scroll <#if springMacroRequestContext.requestUri?starts_with("/commands")>active</#if>">
                        <a href="/commands">Команды </a>
                    </li>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <!--если не авторизован-->
                    <@security.authorize access="!isAuthenticated()">
                        <li class="scroll <#if springMacroRequestContext.requestUri?starts_with("/registration")>active</#if>">
                            <a href="/registration"> Регистрация </a>
                        </li>
                        <li class="scroll <#if springMacroRequestContext.requestUri?starts_with("/login")>active</#if>">
                            <a href="/login">Вход</a>
                        </li>
                    </@security.authorize>

                    <!--если авторизован-->
                    <@security.authorize access="isAuthenticated()">
                        <li class="scroll <#if springMacroRequestContext.requestUri?starts_with("/profile")>active</#if>">
                            <a href="/profile">Профиль</a>
                        </li>
                        <li class="scroll">
                            <a href="/logout">Выход</a>
                        </li>
                    </@security.authorize>
                </ul>
            </div>
        </div>
        <!--/.container-->
    </nav>
    <!--/nav-->
</header>


<@content/>

<footer id="footer">
    <div class="container text-center">
        Управление проектами © 2018 |
        <a href="">Высшая Школа ИТИС </a>
        <a href="http://kpfu.ru">КФУ</a>
    </div>
</footer>
<!--/#footer-->

<script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.form-validator.min.js"></script>
    <script>
        $.validate();
</script>

</body>

</html>
