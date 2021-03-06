<#include "base.ftl">
<#macro title>Список турниров</#macro>
<#macro extrahead>
    <link href="/css/tournaments.css" rel="stylesheet">
    <link href="/css/jquery-ui.css" rel="stylesheet">

    <script src="//widget.cloudinary.com/global/all.js" type="text/javascript"></script>
</#macro>
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
                <select name="discipline" class="form-control" id="disciplineSelect">
                    <option selected value="all">Все</option>
                    <#list all_disciplines as discipline>
                        <option value="${discipline.id}">${discipline.name}</option>
                    </#list>
                </select>

                <@security.authorize access="hasRole('ROLE_MANAGER')">
                  <button class="btn btn-primary btn-block mar-top-30" data-toggle="modal"
                          data-target="#createModal" id="createModalBtn" name="openModal">Создать турнир
                  </button>
                </@security.authorize>

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
                                    <select name="discipline" class="form-control" id="discipline" required>
                                        <option value="-1" disabled selected>Выбрать дисциплину</option>
                                        <#list all_disciplines as discipline>
                                            <option value="${discipline.id}">${discipline.name}</option>
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
                                            <div class="input-group">
                                                <input type="text" class="form-control" maxlength="128"
                                                       name="photo">
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn btn-primary" id="selectPhoto">
                                                        Загрузить
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <label for="startDate">Дата начала</label>
                                        <!--format: "dd.mm.yyyy" look at script below-->
                                        <input id="startDate" name="startDate" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="description">Описание</label>
                                    <textarea class="form-control" id="description" name="description"
                                              rows="3"></textarea>
                                </div>

                                <#if creationError??>
                                    <div class="form-group error" id="forgot-error">
                                        <h5 class="text-center">${creationError}</h5>
                                    </div>
                                </#if>

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

                            <#list upcoming as tournament>
                                <div class="list-group shadowed">
                                    <div class="list-group-item list-group-item-action ov-h"
                                         data-id="${tournament.id}">
                                        <a
                                      <@security.authorize access="hasRole('ROLE_MANAGER')">
                                        href="/tournaments/${tournament.id}/requests"
                                      </@security.authorize>
                                        >
                                            <span class="square"
                                                  style="background-image: url('${tournament.photoLink!"/images/work_2.jpg"}')"></span>
                                            <h4 class="tName">${tournament.name}</h4>
                                            <p>${tournament.description!""}</p>
                                        </a>
                                        <input type="hidden" name="tournamentId" value="${tournament.id}">
                                        <button class="btn btn-primary right" data-toggle="modal"
                                                data-target="#requestModal"
                                                id="requestModalBtn1" name="openModal">Подать заявку
                                        </button>
                                    </div>
                                </div>
                            <#else>
                                <h4 class="mar-top-30">Турниров пока нет...</h4>
                            </#list>
                    </div>

                    <!--Начавшиеся турниры-->
                    <div class="tab-pane fade" id="progress">
                            <#list active as tournament>
                                <div class="list-group shadowed">
                                    <a href="/tournaments/${tournament.id}/"
                                       class="list-group-item list-group-item-action ov-h" data-id="${tournament.id}">
                                        <span class="square"
                                              style="background-image: url('${tournament.photoLink!"/images/work_2.jpg"}')"></span>
                                        <h4>${tournament.name}</h4>
                                        <p>${tournament.description!""}</p>
                                    </a>
                                </div>
                            <#else>
                                <h4 class="mar-top-30">Турниров пока нет...</h4>
                            </#list>
                    </div>

                    <!--Завершенные турниры-->
                    <div class="tab-pane fade" id="past">
                        <#list past as tournament>
                            <a href="/tournaments/${tournament.id}/" class="list-group-item list-group-item-action ov-h"
                               data-id="${tournament.id}">
                                <span class="square"
                                      style="background-image: url('${tournament.photoLink!"/images/work_2.jpg"}')"></span>
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

                        <form class="form" method="post" action="/tournaments/apply">
                            <!--tournament id-->
                            <input type="hidden" name="tournamentId" id="modalTournamentId">

                            <div class="form-group">
                                <h5>Для участия в турнире выберите свою команду или создайте новую</h5>
                                <label class="sr-only">Team</label>
                                <select name="teamId" class="form-control" id="teamId">
                                    <option value="-1">Создать команду</option>
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
                                <h5 class="text-center" style="display: none;">Пользователя с таким e-mail нет в нашей
                                    базе данных</h5>
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

<#macro scripts>
    <script src="/js/jquery-ui.js"></script>
    <script src="/js/datepicker-ru.js"></script>
    <script>
        $('#startDate').datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: "2017:+nn"
        });
    </script>

    <script src="js/jquery.validate.min.js"></script>
    <script src="js/messages_ru.min.js"></script>

    <script>


        $('#form').validate({
            rules: {
                name: {
                    required: true
                    // sorry, regex is not working :(
                    // regex : /^[\d\s]+$/
                },
                discipline: {
                    required: true
                },
                startDate: {
                    required: true
                }
            }
        });
        $("#startDate").rules("add", { regex: "^(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})$" })
    </script>


    <script>
        $('button[name="openModal"]').click(insertModalData);

        // Вставляем данные турнира в модальное окно (форму отправки заявки)
        function insertModalData(e) {
            var selectedTournament = $(e.target).parent();
            // console.log(selectedTournament);
            var tournamentName = selectedTournament.find("h4").html();
            var tournamentId = selectedTournament.find("input[name='tournamentId']").val();

            $('#modalTournamentName').text('Заявка на участие в турнире "' + tournamentName + '"');
            $('#modalTournamentId').val(tournamentId);
        }

        // Прячем форму создания команды, если выбрана существующая команда
        $('#teamId').on('change', function (e) {
            var selectedOption = $("option:selected", this);
            var selectedValue = this.value;

            if (selectedValue === "-1") {
                teamFormDisplay(true);
            }
            else {
                teamFormDisplay(false);
            }
        });

        // Включаем/Выключаем поля формы создания команды
        function teamFormDisplay(bool) {
            var form = $('#createTeamForm');
            if (bool) {
                form.show('slow');
                form.find('input[id="teamName"]').prop('disabled', false);
                form.find('input[id="player1"]').prop('disabled', false);
                form.find('input[id="player2"]').prop('disabled', false);
                form.find('input[id="player3"]').prop('disabled', false);
                form.find('input[id="player4"]').prop('disabled', false);
                form.find('input[id="player5"]').prop('disabled', false);
            }
            else {
                form.hide('slow');
                form.find('input[id="teamName"]').prop('disabled', true);
                form.find('input[id="player1"]').prop('disabled', true);
                form.find('input[id="player2"]').prop('disabled', true);
                form.find('input[id="player3"]').prop('disabled', true);
                form.find('input[id="player4"]').prop('disabled', true);
                form.find('input[id="player5"]').prop('disabled', true);
            }
        }

        // Обновляем страницу при изменении дисциплины
        $('#disciplineSelect').on('change', function (e) {
            var selectedValue = this.value;
            var currentURL = 'http://' + window.location.hostname + window.location.pathname;
            // window.location.href = currentURL + '?discipline=' + selectedValue;
        });
    </script>
    <script>
        $('#selectPhoto').on('click', function (e) {
            cloudinary.openUploadWidget({
                cloud_name: 'dsjur1mdb',
                upload_preset: 'ciep6t8f',
                multiple: false
            }, function (error, result) {
                console.log(error, result);
                var url = result[0].url;
                $('input[name="photo"]').val(url);
            });
        });
    </script>

</#macro>