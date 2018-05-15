<#include "base.ftl">
<#macro title>Дисциплины</#macro>
<#macro content>

<div class="content">
    <div class="container mar-top-30 tournaments">
        <div class="section-header">
            <h2 class="section-title text-center">ДИСЦИПЛИНЫ</h2>
        </div>

        <div class="row">

            <!--Выбор дисциплины-->
    <@security.authorize access="hasAnyRole('MANAGER')">
            <div class="col-md-2 col-md-push-10 col-sm-12 col-xs-12 mar-bot-10">
                <button class="btn btn-primary fixed-button" data-toggle="modal" data-target="#teamCreateModal">Создать
                    диспиплину
                </button>
            </div>
    </@security.authorize>

            <!--вкладки с типами турниров-->
            <div class="col-md-10 col-md-pull-2">

                <!--for discipline in list-->

                <#list disciplines as discipline>
                <div class="col-xs-12 dis-block">
                    <div class="col-lg-10 col-sm-9 col-xs-12">
                        <input type="hidden" name="disId" value="${discipline.id}">
                        <span class="square"
                              style="background-image:url('${discipline.photoLink}');"></span>
                        <h4 class="dis-name">${discipline.name}</h4>
                        <h5 class="">Количество человек в команде: <span class="dis-count">${discipline.teamSize}</span>
                        </h5>
                        <p class="dis-description">
                            <#if discipline.description??>${discipline.description}</#if>
                        </p>
                    </div>
                <@security.authorize access="hasAnyRole('MANAGER')">
                    <div class="col-lg-2 col-sm-3 col-xs-9 mar-top-10">
                        <button id="edit" name="openModal" class="btn btn-primary right" data-toggle="modal"
                                data-target="#teamEditModal">Редактировать
                        </button>
                    </div>

                    <div class="col-lg-2 col-sm-3 col-xs-3 mar-top-10">
                        <form action="/disciplines/${discipline.id}/delete" method="post">
                            <button class="btn btn-default right" type="submit">Удалить</button>
                        </form>
                    </div>
                </@security.authorize>
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
                        <h4 class="text-center" id="modalTournamentName">Создание дисциплины</h4>
                        <br>
                        <form class="form" action="/disciplines/new" method="post">
                            <div class="discipline-create" id="createForm">
                                <div class="form-group">
                                    <label>Название</label>
                                    <input type="text" class="form-control" required name="name" id="new_name">
                                </div>

                                <div class="form-group">
                                    <label>Описание</label>
                                    <textarea name="description" id="new_description" rows="3" class="form-control"
                                              style="resize: vertical;
                                    max-height: 300px; min-height: 40px"></textarea>
                                </div>

                                <div class="form-group">
                                    <label>Количество игроков</label>
                                    <input type="text" class="form-control" placeholder="Первый игрок"
                                           required id="new_count" value="5" max="10" min="1" name="players">
                                </div>

                                <div class="form-group">
                                    <label>Ссылка на логотип</label>
                                    <input type="text" class="form-control mar-bot-10" name="photo_link">
                                </div>
                            </div>
                            <button class="btn btn-block btn-primary" type="submit" id="createSubmit">Создать
                                дисциплину
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
                        <h4 class="text-center" id="modalName">Редактирование дисциплины </h4>
                        <br>
                        <form class="form" method="post" action="/disciplines/update">
                            <!--team id-->
                            <input type="hidden" name="discipline_id" id="edit_id">

                            <div class="discipline-create" id="editForm">
                                <div class="form-group">
                                    <label>Название</label>
                                    <input type="text" class="form-control" required name="name" id="edit_name">
                                </div>

                                <div class="form-group">
                                    <label>Описание</label>
                                    <textarea name="description" id="edit_description" rows="3" class="form-control"
                                              style="resize: vertical;
                                    max-height: 300px; min-height: 40px"></textarea>
                                </div>

                                <div class="form-group">
                                    <label>Количество игроков</label>
                                    <input type="text" class="form-control mar-bot-10"
                                           required id="edit_count" max="10" min="1" name="players">
                                </div>

                                <div class="form-group">
                                    <label>Ссылка на логотип</label>
                                    <input type="text" class="form-control mar-bot-10" name="photo_link">
                                </div>
                            </div>
                            <button class="btn btn-block btn-primary" type="submit" id="editSubmit">Сохранить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </div><!--/.container-->
</div>

</#macro>


<script>

    $('button[name="openModal"]').click(insertModalData);

    // Вставляем данные команды в модальное окно (форму редактирования команды)
    function insertModalData(e) {
        var selectedDiscipline = $(e.target).parent().parent();
        // console.log(selectedDiscipline);
        var disciplineName = selectedDiscipline.find("h4[class='dis-name']").html();
        var disciplineDescription = selectedDiscipline.find("p[class='dis-description']").html().trim();
        var disciplineCount = selectedDiscipline.find("span[class='dis-count']").html();
        var disciplineId = selectedDiscipline.find("input[name='disId']").val();

        console.log(disciplineName);
        console.log(disciplineId);
        console.log(disciplineDescription);
        console.log(disciplineCount);

        $('#modalName').text('Редактирование дисциплины "' + disciplineName + '"');
        $('#edit_name').val(disciplineName);
        $('#edit_id').val(disciplineId);
        $('#edit_count').val(disciplineCount);
        $('#edit_description').val(disciplineDescription);

    }

</script>

<script>
    $('document').ready(function () {
        $('textarea').each(function () {
                    $(this).val($(this).val().trim());
                }
        );
    });
</script>

