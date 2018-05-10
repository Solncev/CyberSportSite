<#include "base.ftl">
<#macro title>Команды</#macro>
<#macro content>


<div class="content">
    <div class="container mar-top-30 tournaments">
        <div class="section-header">
            <h2 class="section-title text-center">МОИ КОМАНДЫ</h2>
        </div>

        <div class="row">

            <!--Выбор дисциплины-->

            <div class="col-md-2 col-md-push-10 col-sm-12 col-xs-12 mar-bot-10">
                <button class="btn btn-primary fixed-button" data-toggle="modal" data-target="#teamCreateModal">Создать
                    команду
                </button>
            </div>


            <!--вкладки с типами турниров-->
            <div class="col-md-10 col-md-pull-2">


                <div class="col-xs-12">
                    <div class="col-lg-4 col-lg-offset-1 col-sm-4 col-xs-6"><h4>Название</h4></div>
                    <div class="col-lg-3 col-sm-4 col-xs-6"><h4>Игроки</h4></div>
                    <div class="col-lg-3 col-sm-4 col-xs-12"></div>
                </div>

                <!--for team in teams-->

                <#list currentUser.teams as team>
                <div class="col-xs-12 team-block">
                    <div class="col-lg-4 col-lg-offset-1 col-sm-4 col-xs-6"><p class="teamName">${team.name}</p></div>
                    <input type="hidden" name="teamId" value="${team.id}">
                    <div class="col-lg-3 col-sm-4 col-xs-6">
                        <!--id == player_команда_игрок-->
                        <p id="player_${team.id}_5">${currentUser.username} - капитан </p>
                        <p id="player_${team.id}_1">${team.firstPlayer}</p>
                        <p id="player_${team.id}_2">${team.secondPlayer}</p>
                        <p id="player_${team.id}_3">${team.thirdPlayer}</p>
                        <p id="player_${team.id}_4">${team.fourthPlayer}</p>
                    </div>

                    <div class="col-sm-4 col-xs-12">
                        <button id="edit1" name="openModal" class="btn btn-primary right" data-toggle="modal"
                                data-target="#teamEditModal">Редактировать
                        </button>
                    </div>
                    <div class="col-sm-4 col-xs-12 mar-top-10">
                        <form action="/teams/delete/${team.id}" method="post">
                            <button class="btn btn-default right">Удалить</button>
                        </form>
                    </div>

                </div>
                </#list>

                <!--end for-->

            </div>
        </div>

        <!--Форма создания команды-->
        <div class="modal fade" id="teamCreateModal" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header no-border">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <h4 class="text-center" id="modalTournamentName">Создание команды</h4>
                        <br>
                        <form class="form" method="post" action="/teams/new">
                            <div class="team-create" id="createTeamForm">
                                <div class="form-group">
                                    <label>Название команды</label>
                                    <input type="text" class="form-control" required name="teamName" id="new_teamName">
                                </div>

                                <div class="form-group">
                                    <label>Никнеймы игроков</label>
                                    <input type="text" class="form-control mar-bot-10" placeholder="Первый игрок"
                                           required name="player1" id="new_player1" maxlength="45">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Второй игрок"
                                           required name="player2" id="new_player2" maxlength="45">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Третий игрок"
                                           required name="player3" id="new_player3" maxlength="45">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Четвертый игрок"
                                           required name="player4" id="new_player4" maxlength="45">
                                </div>
                            </div>
                            <button class="btn btn-block btn-primary" id="createSubmit" type="submit">Создать команду
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!--Форма редактирования команды-->
        <div class="modal fade" id="teamEditModal" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header no-border">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <h4 class="text-center" id="modalTeamName">Редактирование команды </h4>
                        <br>
                        <form class="form" method="post" action="/teams/update">
                            <!--team id-->
                            <input type="hidden" name="teamId" id="modalTeamId">

                            <div class="team-create" id="editTeamForm">
                                <div class="form-group">
                                    <label>Название команды</label>
                                    <input type="text" class="form-control" required name="teamName" id="teamName">
                                </div>

                                <div class="form-group">
                                    <label>Никнеймы игроков</label>
                                    <input type="text" class="form-control mar-bot-10" placeholder="Первый игрок"
                                           required name="player1" id="player1" maxlength="45">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Второй игрок"
                                           required name="player2" id="player2" maxlength="45">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Третий игрок"
                                           required name="player3" id="player3" maxlength="45">
                                    <input type="text" class="form-control mar-bot-10" placeholder="Четвертый игрок"
                                           required name="player4" id="player4" maxlength="45">
                                </div>
                            </div>
                            <button class="btn btn-block btn-primary" id="editSubmit">Сохранить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </div><!--/.container-->
</div>
<!--Турниры - tournaments -->


</#macro>

<#macro scripts>

<script>
    $('button[name="openModal"]').click(insertModalData);

    // Вставляем данные команды в модальное окно (форму редактирования команды)
    function insertModalData(e) {
        var selectedTeam = $(e.target).parent().parent();
        var teamName = selectedTeam.find("p[class='teamName']").html();
        var teamId = selectedTeam.find("input[name='teamId']").val();

        $('#modalTeamName').text('Редактирование команды "' + teamName + '"');
        $('#modalTeamId').val(teamId);

        // Название и игроки
        $('#teamName').val(teamName);
        var teamSize = [1, 2, 3, 4, 5];
        teamSize.forEach(function (value) {
            var playerName = $('#player_' + teamId + '_' + value).html();
            $('#player' + value).val(playerName);
        });
    }

    var error_p = $("<p>" + 'Команда с таким названием уже существует' + "</p>");
    error_p.addClass('error');

    // Проверяем существование команды с введенным названием
    $('#new_teamName').change(function () {
        var input = $(this);
        $.ajax({
            url: '/teams/check',
            data: {'name': input.val()},
            success: function (free) {
                if (!free) {
                    input.parent().addClass('has-error');
                    input.parent().append(error_p);
                    $('#createSubmit').prop('disabled', true)
                }
                else {
                    input.parent().removeClass('has-error');
                    error_p.remove();
                    $('#createSubmit').prop('disabled', false)
                }
            }
        });
    });

</script>

</#macro>