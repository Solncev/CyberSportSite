<#include "base.ftl">
<#macro title>${tournament.name} | ${match.team1.name} - ${match.team2.name}</#macro>
<#macro content>

<div class="content">
    <div class="container mar-top-30 tournaments">
        <div class="section-header">
            <h2 class="section-title text-center">ТУРНИР "${tournament.name}"</h2>
        </div>

        <div class="row">
            <#if match.winner?has_content>
            <h2 class="section-title text-center">Игра завершилась победой команды "<#if match.winner ==1>${match.team1.name}<#else>${match.team2.name}</#if>"</h2>
            <#elseif match.team1Winner?has_content && match.team2Winner?has_content && match.team1Winner != match.team2Winner>
            <h2 class="section-title text-center">Спорная ситуация</h2>
            <#else>
            <h2 class="section-title text-center">Игра идет</h2>
            </#if>
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
                <#if match.description?has_content>
                <div class="col-xs-12">
                    <p class="text-center text-underlined mar-top-10">
                        <a href="${match.description}">Ссылка на твич: ${match.description}</a>
                    </p>
                </div>
                </#if>
                <#if currentUser.id == match.team1.leader.id || currentUser.id == match.team2.leader.id>
                    <#if !match.description?has_content>
                    <form class="form" method="post" action="${match.id}/attach">
                        <div class="col-xs-8 col-md-9">
                            <input type="text" name="link" class="form-control" placeholder="Введите ссылку на твич">
                        </div>
                        <button type="submit" class="btn btn-default col-xs-4 col-md-3">Прикрепить</button>
                    </form>
                    </#if>
                </#if>
            </div>


            <#if !match.winner?has_content>
                <div class="col-md-8 col-md-offset-2 mar-top-30 ov-h">
                    <div class="col-xs-12">
                    <#if (currentUser.id == match.team1.leader.id && !match.team1Winner?has_content) || (currentUser.id == match.team2.leader.id && !match.team2Winner?has_content)>
                        <form class="form" method="post" action="${match.id}/send_result">
                            <button name="result" value="<#if currentUser.id == match.team1.leader.id>1<#else>2</#if>" type="submit" class="btn btn-success right">&nbsp;Мы выиграли&nbsp;</button>
                            <button name="result" value="<#if currentUser.id == match.team1.leader.id>2<#else>1</#if>" type="submit" class="btn btn-danger">Мы проиграли</button>
                        </form>
                    <#elseif (currentUser.id == match.team1.leader.id || currentUser.id == match.team2.leader.id) && (!match.team1Winner?has_content || !match.team2Winner?has_content)>
                         <p class="text-center">В ожидании ответа другой команды</p>
                    <#elseif match.team1Winner?has_content && match.team2Winner?has_content>
                        <@security.authorize access="hasAnyRole('ROLE_MANAGER')">
                        <form class="form" method="post" action="${match.id}/resolve_conflict">
                            <button name="result" value="1" type="submit" class="btn btn-success">Победили "${match.team1.name}"</button>
                            <button name="result" value="2" type="submit" class="btn btn-success right">Победили "${match.team2.name}"</button>
                        </form>
                        </@security.authorize>
                    </#if>
                    </div>
                </div>
            </#if>

        </div>

        <div class="row outer">
            <div class="tournament-table">

            <#list 1..4 as round_index>

                <div class="col-xs-2 <#if round_index==1>col-xs-offset-1</#if> round-${round_index?c}">

                    <#list matches_grid[round_index?c] as tournament_match>
                        <div class="pair">
                            <#if tournament_match.winner?has_content>
                                <input type="text" disabled value="${tournament_match.team1.name}" class="<#if tournament_match.winner==1>green<#else>red</#if>-border">
                                <input type="text" disabled value="${tournament_match.team2.name}" class="<#if tournament_match.winner==2>green<#else>red</#if>-border">
                            <#elseif tournament_match.team1Winner?has_content && tournament_match.team2Winner?has_content>
                                <input type="text" disabled value="${tournament_match.team1.name}" class="yellow-border">
                                <input type="text" disabled value="${tournament_match.team2.name}" class="yellow-border">
                            <#else>
                                <input type="text" disabled value="<#if tournament_match.team1?has_content>${tournament_match.team1.name}<#else>?</#if>" class="default-border">
                                <input type="text" disabled value="<#if tournament_match.team2?has_content>${tournament_match.team2.name}<#else>?</#if>" class="default-border">
                            </#if>
                        </div>
                    </#list>
                </div>

            </#list>

                <div class="col-xs-2 round-5">
                    <div class="pair">
                        <#assign last_match = matches_grid['4'][0] >
                        <#if last_match.winner?has_content>
                            <input type="text" disabled value="<#if last_match.winner == 1>${last_match.team1.name}<#else>${last_match.team2.name}</#if>" class="green-border">
                        <#else>
                            <input type="text" disabled value="?" class="default-border">
                        </#if>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

    </div><!--/.container-->
</div>
<!--Турниры - tournaments -->


</#macro>