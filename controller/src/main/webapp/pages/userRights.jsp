<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 25.01.2016
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Права и пользователи</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" rel="stylesheet">
    <%--<link href="style.css" rel="stylesheet">--%>
    <link href="pages/resources/css/prometheus.css" rel="stylesheet">
    <link href="https://raw.githubusercontent.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="/header"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-md-10 col-md-offset-2 main">
            <h3>
                <div class="form-group user-title-group">
                    <b>Пользователи</b>
                </div>
                <div class="form-group user-add-group">
                    <a href="/pages/userAdd.jsp" id="user-add-button" name="user-add"
                       class="btn  btn-success pull-right" title="Добавить пользователя">Добавить пользователя</a>
                    <%--<input id="user-add-button" name="user-add-button"--%>
                           <%--type="button"--%>
                           <%--value="Добавить пользователя"--%>
                           <%--class="btn btn-primary pull-right" data-toggle="modal"--%>
                           <%--data-target="#myModal2">--%>
                </div>
            </h3>
            <table class="table">
                <thead>
                <tr>
                    <th>Имя пользователя</th>
                    <th>Email пользователя</th>
                    <th>Роль пользователя в системе</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                                ${user.getName()}
                        </td>
                        <td>
                                ${user.getEmail()}
                        </td>
                        <td>
                                ${user.getRole().getName()}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://rawgit.com/moment/moment/develop/moment.js"></script>
<script src="https://rawgit.com/moment/moment/master/locale/ru.js"></script>
<script src="https://rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>
<script src="https://rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/js/bootstrap-datetimepicker.min.js"></script>

</body>
</html>
