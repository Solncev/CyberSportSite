<#include 'base.ftl'>
<#macro title>${tournament.name}</#macro>
<#macro extrahead>
    <link href="/css/game.css" rel="stylesheet">
    <link href="/css/tournaments.css" rel="stylesheet">
</#macro>
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
        </div>

        <#-- todo copypasta from TournamentMatchController -->
        <div class="row outer">
            <div class="tournament-table">

            <#list 1..4 as round_index>

                <div class="col-xs-2 <#if round_index==1>col-xs-offset-1</#if> round-${round_index?c}">

                    <#list matches_grid[round_index?c] as tournament_match>

                        <a <#if tournament_match.team1?? && tournament_match.team2??>href="/tournament_matches/${tournament_match.id}"</#if>>
                            <div class="pair">
                            <#assign t1name = tournament_match.team1???then(tournament_match.team1.name, '-') />
                            <#assign t2name = tournament_match.team2???then(tournament_match.team2.name, '-') />
                            <#if tournament_match.winner?has_content>
                                <input type="text" disabled value="${t1name}" class="<#if tournament_match.winner==1>green<#else>red</#if>-border">
                                <input type="text" disabled value="${t2name}" class="<#if tournament_match.winner==2>green<#else>red</#if>-border">
                            <#elseif tournament_match.team1Winner?has_content && tournament_match.team2Winner?has_content>
                                <input type="text" disabled value="${t1name}" class="yellow-border">
                                <input type="text" disabled value="${t2name}" class="yellow-border">
                            <#else>
                                <input type="text" disabled value="<#if tournament_match.team1?has_content>${tournament_match.team1.name}<#else>?</#if>" class="default-border">
                                <input type="text" disabled value="<#if tournament_match.team2?has_content>${tournament_match.team2.name}<#else>?</#if>" class="default-border">
                            </#if>
                            </div>
                        </a>
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

<#macro scripts></#macro>