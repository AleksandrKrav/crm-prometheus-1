<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 26.01.2016
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>CRM-Prometheus</title>
    <link href="<c:url value="/pages/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/pages/resources/css/prometheus.css"/>" rel="stylesheet">

    <script src="<c:url value="https://code.jquery.com/jquery-2.1.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="http://code.jquery.com/ui/1.10.4/jquery-ui.js"/>" type="text/javascript"></script>
</head>
<body>
<jsp:include page="/header"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <div class="panel contact-panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Добавить пользователя</h3>
                </div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <fieldset class="field-user">
                            <form class="form-horizontal" action="/user_add" method="POST" id="form-add-user">
                                <c:if test="${param.errorUserName != null}">
                                    <div class="alert alert-danger">
                                        <p>Минимальная длина имени 3 символа</p>
                                    </div>
                                </c:if>
                                <c:if test="${param.errorEmail == 1}">
                                    <div class="alert alert-danger">
                                        <p>Ошибка при вводе емейла</p>
                                    </div>
                                </c:if>
                                <c:if test="${param.errorEmail == 2}">
                                    <div class="alert alert-danger">
                                        <p>Такой емейл уже существует.</p>
                                    </div>
                                </c:if>
                                <c:if test="${param.errorPass != null}">
                                    <div class="alert alert-danger">
                                        <p>Длина пароля должна быть не меньше 8 символов.</p>
                                    </div>
                                </c:if>
                                <div class="form-group user-email-group">
                                    <label for="email"
                                           class="col-md-4 control-label">Email
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control"
                                               id="email"
                                               name="email"
                                               size="30" required autofocus>
                                    </div>
                                </div>
                                <div class="form-group user-name-group">
                                    <label for="username"
                                           class="col-md-4 control-label">Имя
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control"
                                               id="username"
                                               name="username"
                                               size="30" required autofocus>
                                    </div>
                                </div>
                                <div class="form-group user-password-group">
                                    <label for="password"
                                           class="col-md-4 control-label">Пароль</label>
                                    <div class="col-md-8">
                                        <input type="password" class="form-control"
                                               id="password"
                                               name="password"
                                               value size="30"
                                               autofocus required>
                                    </div>
                                </div>
                                <div class="form-group user-admin-group">
                                    <label for="user_admin"
                                           class="col-md-4 control-label">Администратор</label>
                                    <div class="col-md-8">
                                        <input type="checkbox" class="form-control"
                                               id="user_admin"
                                               name="user_admin"
                                               value="admin" size="30"
                                               autofocus>
                                    </div>
                                </div>
                                <div class="form-group pull-right">
                                    <input id="user_add" name="user_add"
                                           type="submit"
                                           value="Сохранить"
                                           class="btn btn-primary"/>
                                    <input id="user-cancel" type="reset"
                                           value="Отмена"
                                           class="btn btn-default"/>
                                </div>
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
