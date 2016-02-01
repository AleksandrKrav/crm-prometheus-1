<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>CRM-Prometheus</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/prometheus.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
    <script src="resources/js/authUser.js" type="text/javascript"></script>

</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel contact-panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Вход в систему</h3>
                </div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <fieldset class="field-contact">
                            <legend>Авторизация</legend>
                            <form class="form-horizontal" action="/userAuth" method="POST" id="form-auth-user">
                                <div class="form-group auth-login-group">
                                    <label for="inputLogin" class="col-md-4 control-label">Имя</label>
                                    <div class="col-md-8">
                                        <input type="text" name="auth-login" class="form-control" id="inputLogin"/>
                                    </div>
                                </div>
                                <div class="form-group auth-pass-group">
                                    <label for="inputPassword" class="col-md-4 control-label">Пароль</label>
                                    <div class="col-md-8">
                                        <input type="password" name="auth-pass" class="form-control" id="inputPassword"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="butLogin" name="but-auth" type="button" value="Войти"
                                               class="btn btn-primary">
                                    </div>
                                </div>
                                <div id="status-login"></div>
                            </form>
                        </fieldset>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <fieldset class="field-contact">
                            <legend>Регистрация</legend>
                            <form class="form-horizontal" action="/userReg" method="POST" id="form-reg-user">
                                <div class="form-group reg-name-group">
                                    <label for="reg-name" class="col-md-4 control-label">Имя</label>
                                    <div class="col-md-8">
                                        <input type="text" name="reg-name" class="form-control" id="reg-name"/>
                                    </div>
                                </div>
                                <div class="form-group reg-login-group">
                                    <label for="reg-login" class="col-md-4 control-label">Логин</label>
                                    <div class="col-md-8">
                                        <input type="text" name="reg-login" class="form-control" id="reg-login"/>
                                    </div>
                                </div>
                                <div class="form-group reg-pass-group">
                                    <label for="reg-pass" class="col-md-4 control-label">Пароль</label>
                                    <div class="col-md-8">
                                        <input type="text" name="reg-pass" class="form-control" id="reg-pass"/>
                                    </div>
                                </div>
                                <div class="form-group reg-email-group">
                                    <label for="reg-email" class="col-md-4 control-label">Email</label>
                                    <div class="col-md-8">
                                        <input type="text" name="reg-email" class="form-control" id="reg-email"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="but-reg" type="button" value="Зарегистрировать"
                                               class="btn btn-primary">
                                    </div>
                                </div>
                                <div id="status-reg"></div>
                            </form>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




