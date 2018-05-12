<#include 'base.ftl'>
<#macro title>${tournament.name}</#macro>
<#macro content>
<div class="content">
    <div class="container mar-top-30 tournaments">
        <div class="section-header">
            <h2 class="section-title text-center">ТУРНИР ${tournament.name}</h2>
        </div>

        <div class="row">
            <div class="ov-h">
                <@security.authorize access="hasRole('ROLE_MANAGER')">
                    <form method="post" action="/tournaments/${tournament.id}/finish">
                        <button type="submit" class="btn btn-default right">Завершить турнир</button>
                    </form>
                </@security.authorize>
            </div>
            <h2 class="section-title text-center">Спорная ситуация</h2>
            <div class="col-md-8 col-md-offset-2 ov-h">
                <div class="col-sm-6 col-xs-12">
                    <h4>Команда 7</h4>
                    <p>Капитан: Никнейм 1</p>
                    <p>Никнейм 2</p>
                    <p>Никнейм 3</p>
                    <p>Никнейм 4</p>
                    <p>Никнейм 5</p>
                </div>
                <div class="col-sm-6 col-xs-12">
                    <div class="right-block">
                        <h4>Команда 8</h4>
                        <p>Капитан: Никнейм 1</p>
                        <p>Никнейм 2</p>
                        <p>Никнейм 3</p>
                        <p>Никнейм 4</p>
                        <p>Никнейм 5</p>
                    </div>
                </div>
            </div>

            <div class="col-md-8 col-md-offset-2 mar-top-30 ov-h">
                <div class="col-xs-12">
                    <button class="btn btn-success">Победили "Команда 7"</button>
                    <button class="btn btn-success right">Победили "Команда 8"</button>
                </div>
            </div>

            <div class="col-xs-12">
                <p class="text-center text-underlined mar-top-10">
                    <a href="https://twitch.com">Ссылка на твич: https://twitch.com</a>
                </p>
            </div>

        </div>

        <div class="row outer">
            <div class="tournament-table">
                <div class="col-xs-2 col-xs-offset-1 round-1">
                    <div class="pair">
                        <input type="text" disabled value="VeryLongteamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="green-border">
                        <input type="text" disabled value="teamName" class="red-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="green-border">
                        <input type="text" disabled value="teamName" class="red-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="green-border">
                        <input type="text" disabled value="teamName" class="red-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="green-border">
                        <input type="text" disabled value="teamName" class="red-border">
                    </div>
                </div>

                <div class="col-xs-2 round-2">
                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="green-border">
                        <input type="text" disabled value="teamName" class="red-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>
                </div>

                <div class="col-xs-2 round-3">
                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>

                    <div class="pair">
                        <input type="text" disabled value="teamName" class="default-border">
                        <input type="text" disabled value="teamName" class="default-border">
                    </div>
                </div>

                <div class="col-xs-2 round-4">
                    <div class="pair">
                        <input type="text" disabled value="teamName" class="yellow-border">
                        <input type="text" disabled value="teamName" class="yellow-border">
                    </div>
                </div>

                <div class="col-xs-2 round-5">
                    <div class="pair">
                        <input type="text" disabled value="teamName" class="default-border">
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

    </div><!--/.container-->
</div>
<!--Турниры - tournaments -->
</#macro>