<#include "base.ftl">
<#macro title>Профиль</#macro>
<#macro content>

<div class="content">

    <!--Турниры - profile -->
    <section id="get-in-touch">
        <div class="container">

            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group row">
                        <label for="colFormLabelSm" class="col-sm-3 col-form-label col-form-label-sm">Никнейм:</label>
                        <div class="col-sm-8">
                            <label id="colFormLabelSm">cool_nickname</label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="colFormLabelSm" class="col-sm-3 col-form-label col-form-label-sm">Email:</label>
                        <div class="col-sm-8">
                            <label id="colFormLabelSm">info@companyname.com</label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-8 col-sm-offset-3">
                            <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#editModal">Редактировать</button>
                        </div>
                        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">

                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="editModalLabel">Modal title</h4>
                                    </div>
                                    <form action="#" method="post" name="editProfileForm" id="editProfileForm">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <input type="text" placeholder="Name" class="form-control" name="name" max="30">
                                            </div>
                                            <div class="form-group">
                                                <input type="email" placeholder="Email" class="form-control" name="email" data-validation="email">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-8 col-sm-offset-3">
                            <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#editPassModal">Сменить пароль</button>
                        </div>
                        <div class="modal fade" id="editPassModal" tabindex="-1" role="dialog" aria-labelledby="passLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <form class="form-horizontal" action="..." method="POST" id="editPassForm">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="passLabel">Modal title</h4>
                                        </div>
                                        <div class="modal-body">

                                            <div class="form-group">
                                                <label for="password" class="col-xs-3 control-label">Старый пароль</label>

                                                <div class="col-xs-6">
                                                    <input type="password" class="form-control" name="oldPassword" id="oldPassword" min="1" maxlength="50" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="password" class="col-xs-3 control-label">Новый пароль</label>

                                                <div class="col-xs-6">
                                                    <input type="password" class="form-control" name="password" id="password" max="50" data-validation-strength="1" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="repeatedPassword" class="col-xs-3 control-label">Повторите пароль</label>
                                                <div class="col-xs-6">
                                                    <input type="password" class="form-control" equalTo="password" name="repeatedPassword" id="repeatedPassword" max="50" data-validation-strength="1"
                                                    />
                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                                            <button class="btn-primary btn" type="submit">Изменить пароль</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-8">

                <label>Commands here</label>
                <!-- <form action="#" method="post" name="edit" id="edit">
                    <label for="username">Uname</label>
                    <input type="text" id="username" placeholder="Name" class="form-control" name="username" />
                    <label for="email">Email</label>
                    <input type="email" id="email" placeholder="Email" class="form-control" name="email" />
                    <button type="button" class="btn btn-primary">Save changes</button>

                </form> -->

            </div>


        </div>


</div>


<!--/#get-in-touch-->
</#macro>
