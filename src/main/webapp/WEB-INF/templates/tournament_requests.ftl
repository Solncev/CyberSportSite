<#include 'base.ftl'>
<#macro title>${tournament.name} | Заявки</#macro>

<#macro content>
      <div class="content">
          <div class="container mar-top-30 tournaments">
              <div class="section-header">
                  <h2 class="section-title text-center">Заявки на турнир: ${tournament.name}</h2>
              </div>

              <div class="row">
                  <!--Форма-->
                  <form action="/tournaments/${tournament.id}/start" method="post" name="requestSelectForm" id="requestSelectForm">
                      <!--Начать турнир-->
                      <div class="col-md-2 col-md-push-10 col-sm-12 col-xs-12 mar-bot-10">
                          <button type="button" class="btn btn-primary fixed-button" data-toggle="modal" data-target="#tournamentStartModal"
                                  <#if .now < tournament.date?datetime>disabled </#if>>
                              Начать турнир
                          </button>
                      </div>

                      <div class="col-md-10 col-md-pull-2">
                          <div class="col-xs-12">
                              <div class="col-lg-4 col-lg-offset-1 col-sm-4 col-xs-6">
                                  <h4>Название</h4>
                              </div>
                              <div class="col-lg-3 col-sm-4 col-xs-6">
                                  <h4>Игроки</h4>
                              </div>
                              <div class="col-lg-offset-1 col-lg-3 col-sm-4 col-xs-12">
                                  <h4>Принять команду</h4>
                              </div>
                          </div>

                          <!--for team in teams-->
                          <#list requests as req>
                            <div class="col-xs-12 team-block">
                                <div class="col-lg-4 col-lg-offset-1 col-sm-4 col-xs-6">
                                    <p class="teamName">${req.team.name}</p>
                                </div>
                                <div class="col-lg-3 col-sm-4 col-xs-6">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            Список игроков
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li>
                                                <a>${req.team.leader.username}</a>
                                            </li>
                                            <li role="separator" class="divider"></li>
                                            <li>
                                                <a>${req.team.firstPlayer}</a>
                                            </li>
                                            <li>
                                                <a>${req.team.secondPlayer}</a>
                                            </li>
                                            <li>
                                                <a>${req.team.thirdPlayer}</a>
                                            </li>
                                            <li>
                                                <a>${req.team.fourthPlayer}</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-lg-offset-2 col-lg-2 colcol-sm-4 col-xs-12">
                                    <div class="checkbox">
                                        <label>
                                            <input name="${req.team.id}" type="checkbox" <#if req.accepted>checked</#if>">
                                        </label>
                                    </div>
                                </div>
                            </div>
                          </#list>
                      </div>
                      <!--Подтвеждение модалка-->
                      <div class="modal fade" id="tournamentStartModal" role="dialog">
                          <div class="modal-dialog modal-sm" role="document">
                              <div class="modal-content">
                                  <div class="modal-header">
                                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                          <span aria-hidden="true">&times;</span>
                                      </button>
                                      <h4 class="modal-title" id="countCheck"></h4>
                                  </div>
                                  <div class="modal-body">
                                      <p id="message">Вы желаете начать турнир?</p>
                                  </div>
                                  <div class="modal-footer">
                                      <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                                      <button type="submit" id="startButton" class="btn btn-primary">Начать</button>
                                  </div>
                              </div>
                              <!-- /.modal-content -->
                          </div>
                          <!-- /.modal-dialog -->
                      </div>
                  </form>
              </div>

          </div>
          <!--/.container-->
      </div>
    <!--Турниры - tournaments -->
</#macro>

<#macro scripts>
      <script>
          $("#tournamentStartModal").on('shown.bs.modal', function (e) {
              let checked = $(":checkbox:checked").length;
              let all = $(":checkbox").length;
              $('#countCheck').text('Вы выбрали ' + checked + ' команд из ' + all);
              if (checked < 2) {
                  document.getElementById("startButton").disabled = true;
                  $('#message').text('Нужно выбрать хотя бы 2 команды');
              } else {
                  document.getElementById("startButton").disabled = false;
                  $('#message').text('Вы желаете начать турнир?');
              }
          });
      </script>
</#macro>