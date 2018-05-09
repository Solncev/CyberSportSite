<#include "base.ftl">
<#macro title>Матч</#macro>
<#macro content>

<div class="content">
    <div class="container mar-top-30 tournaments">
        <div class="section-header">
            <h2 class="section-title text-center">ТУРНИР "${tournament.name}"</h2>
        </div>

        <div class="row">
            <h2 class="section-title text-center">Игра идет</h2>
            <div class="col-md-8 col-md-offset-2 ov-h">
                <div class="col-sm-6 col-xs-12">
                    <h4>Команда "${match.team1.name}"</h4>
                    <p>Капитан: ${match.team1.leader.username}</p>
                    <p>${match.team1.firstPlayer}</p>
                    <p>${match.team1.secondPlayer}</p>
                    <p>${match.team1.thirdPlayer}</p>
                    <p>${match.team1.fourthPlayer}</p>
                </div>
                <div class="col-sm-6 col-xs-12">
                    <div class="right-block">
                        <h4>Команда "${match.team2.name}"</h4>
                        <p>Капитан: ${match.team2.leader.username}</p>
                        <p>${match.team2.firstPlayer}</p>
                        <p>${match.team2.secondPlayer}</p>
                        <p>${match.team2.thirdPlayer}</p>
                        <p>${match.team2.fourthPlayer}</p>
                    </div>
                </div>
                <div class="col-xs-8 col-md-9">
                    <input type="text" class="form-control" placeholder="Введите ссылку на твич">
                </div>
                <button class="btn btn-default col-xs-4 col-md-3">Прикрепить</button>
            </div>

            <div class="col-md-8 col-md-offset-2 mar-top-30 ov-h">
                <div class="col-xs-12">
                    <button class="btn btn-success right">&nbsp;Мы выиграли&nbsp;</button>
                    <button class="btn btn-danger">Мы проиграли</button>
                </div>
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
                        <input type="text" disabled value="teamName" class="green-border">
                        <input type="text" disabled value="teamName" class="red-border">
                    </div>
                </div>

                <div class="col-xs-2 round-4">
                    <div class="pair">
                        <input type="text" disabled value="teamName" class="red-border">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>
                </div>

                <div class="col-xs-2 round-5">
                    <div class="pair">
                        <input type="text" disabled value="teamName" class="green-border">
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

    </div><!--/.container-->
</div>
<!--Турниры - tournaments -->


</#macro>