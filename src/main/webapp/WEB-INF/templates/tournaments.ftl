<#include "base.ftl">
<#macro title>Список турниров</#macro>
<#macro content>

<div class="content">
    <div class="container mar-top-30 tournaments">
        <div class="section-header">
            <h2 class="section-title text-center">Турниры</h2>
        </div>

        <div class="row">

            <!--Выбор дисциплины-->
            <div class="col-md-2 col-md-push-10 col-sm-4 col-xs-12 mar-bot-10">
                <label>Дисциплина</label>
                <select name="" class="form-control" id="disciplineSelect">
                    <option selected value="all">Все</option>
                    <#list all_games as game>
                        <option value="${game.id}">${game.name}</option>
                    </#list>
                </select>

                <button class="btn btn-primary btn-block mar-top-30" data-toggle="modal"
                        data-target="#createModal" id="createModalBtn" name="openModal">Создать турнир
                </button>


            </div>
            <div class="modal fade" id="createModal" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header no-border">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <h4 class="text-center">Создание турнира </h4>
                            <br>

                            <@sf.form class="form" action="/tournaments/new" method="POST" modelAttribute="form">
                                <div class="form-group">
                                    <label>Дисциплина</label>
                                    <select name="game" class="form-control" id="game">
                                        <option value="-1">Выбрать дисциплину</option>
                                        <#list all_games as game>
                                            <option value="${game.id}">${game.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Название турнира</label>
                                    <input type="text" class="form-control" required name="name" id="name">
                                </div>
                                <div class="row">
                                    <div class="col-xs-6">
                                        <div class="form-group">
                                            <label for="photo">Фото</label>
                                            <#-- TODO file upload -->
                                            <#--<input type="file" id="photo" name="photo">-->
                                            <input type="text" id="photo" name="photo">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <label for="startDate">Дата начала</label> <!--format: "dd.mm.yyyy" look at script below-->
                                        <input id="startDate" name="startDate" type="text" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="description">Описание</label>
                                    <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                                </div>

                                <div class="form-group error" id="forgot-error">
                                    <h5 class="text-center" style="display: none;">ERROR MESSAGE</h5>
                                </div>

                                <button class="btn btn-block btn-primary" id="forgot-submit">Создать турнир</button>
                            </@sf.form>
                        </div>
                    </div>
                </div>
            </div>

            <!--вкладки с типами турниров-->
            <div class="col-md-10 col-md-pull-2">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#future" data-toggle="tab">Предстоящие</a>
                    </li>
                    <li class="">
                        <a href="#progress" data-toggle="tab">Начавшиеся</a>
                    </li>
                    <li class="">
                        <a href="#past" data-toggle="tab">Завершенные</a>
                    </li>
                </ul>

                <div id="tabs" class="tab-content">

                    <!--Предстоящие турниры-->
                    <div class="tab-pane fade active in" id="future">
                        <div class="list-group shadowed">
                            <#list upcoming as tournament>
                                <a href="#" class="list-group-item list-group-item-action ov-h"
                                   data-player-count="${tournament.game.teamSize}">
                                    <span class="square" style="background-image: url('images/work_4.jpg')"></span>
                                    <h4 class="tName">${tournament.name}</h4>
                                    <p>${tournament.description!""}</p>
                                    <input type="hidden" name="tournamentId" value="${tournament.id}">
                                    <button class="btn btn-primary right" data-toggle="modal" data-target="#requestModal"
                                            id="requestModalBtn1" name="openModal">Подать заявку
                                    </button>
                                </a>
                            <#else>
                                <h4 class="mar-top-30">Турниров пока нет...</h4>
                            </#list>
                        </div>
                    </div>

                    <!--Начавшиеся турниры-->
                    <div class="tab-pane fade" id="progress">
                        <div class="list-group shadowed">
                            <#list active as tournament>
                                <a href="#" class="list-group-item list-group-item-action ov-h">
                                    <span class="square" style="background-image: url('images/work_2.jpg')"></span>
                                    <h4>${tournament.name}</h4>
                                    <p>${tournament.description!""}</p>
                                </a>
                            <#else>
                                <h4 class="mar-top-30">Турниров пока нет...</h4>
                            </#list>
                        </div>
                    </div>

                    <!--Завершенные турниры-->
                    <div class="tab-pane fade" id="past">
                        <#list past as tournament>
                            <a href="#" class="list-group-item list-group-item-action ov-h">
                                <span class="square" style="background-image: url('images/work_2.jpg')"></span>
                                <h4>${tournament.name}</h4>
                                <p>${tournament.description!""}</p>
                            </a>
                        <#else>
                            <h4 class="mar-top-30">Турниров пока нет...</h4>
                        </#list>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="requestModal" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header no-border">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h4 class="text-center" id="modalTournamentName">Заявка на участие в турнире </h4>
                        <br>

                        <form class="form">
                            <!--tournament id-->
                            <input type="hidden" name="" id="modalTournamentId">

                            <div class="form-group">
                                <h5>Для участия в турнире выберите свою команду или создайте новую</h5>
                                <label class="sr-only">Team</label>
                                <select name="" class="form-control" id="teamSelect">
                                    <option value="create">Создать команду</option>
                                    <#list currentUser.teams as team>
                                        <option value="${team.id}">${team.name}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="team-create" id="createTeamForm">
                                <div class="form-group">
                                    <label>Название команды</label>
                                    <input type="text" class="form-control" required name="teamName" id="teamName">
                                </div>

                                <div class="form-group">
                                    <label>Никнеймы игроков</label>
                                    <input type="text" class="form-control mar-bot-10" placeholder="Первый игрок"
                                           required name="player1" id="player1">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Второй игрок"
                                           required name="player2" id="player2">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Третий игрок"
                                           required name="player3" id="player3">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Четвертый игрок"
                                           required name="player4" id="player4">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Пятый игрок"
                                           required name="player5" id="player5">
                                </div>

                            </div>

                            <div class="form-group error" id="forgot-error">
                                <h5 class="text-center" style="display: none;">Пользователя с таким e-mail нет в нашей базе данных</h5>
                            </div>

                            <button class="btn btn-block btn-primary" id="forgot-submit">Подать заявку</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </div>
    <!--/.container-->
</div>

</#macro>