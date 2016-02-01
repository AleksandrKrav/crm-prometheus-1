<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 17.12.2015
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список сделок</title>
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
                <b>Список сделок</b>
            </h3>
                <div class="btn-group pull-left" role="group" id="funnel-list-buttons">
                    <a href="funnel_of_deals" class="btn btn-info" id="funnel-of-deals">Воронка</a>
                    <a href="list_of_deals" class="btn btn-default " id="list-of-deals">Список</a>
                </div>
                <a href="add_deal" class="btn btn-info pull-right" id="deal-add-button" title="Добавить Сделку">Добавить
                    сделку</a>

            <table class="table">
                <thead>
                <tr>
                    <th>Название сделки</th>
                    <th>Основной контакт</th>
                    <th>Компания контакта</th>
                    <th>Этап сделки</th>
                    <th>Бюджет, валюта</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${deals}" var="deal">
                    <tr>
                        <td>
                                ${deal.getName()}
                        </td>
                        <td>
                                ${contact}
                        </td>
                        <td>
                                ${deal.getCompany().getName()}
                        </td>
                        <td>
                                ${deal.getStatus().getName()}
                        </td>
                        <td>
                                ${deal.getBudget()}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<%--<div class="container">--%>
<%--<!----%>
<%--<header></header>--%>
<%--<nav></nav>--%>

<%--<div class="heading">--%>


<%--</div>--%>
<%---->--%>

<%--<div class="row">--%>
<%--<aside class="col-xs-3 col-sm-3 col-md-3 ">--%>
<%--<p>--%>
<%--<ul class="nav nav-pills">--%>
<%--<li class="active">--%>
<%--<a href="funnel_of_deals">Воронка</a>--%>
<%--</li>--%>
<%--<li><a href="list_of_deals">Список</a></li>--%>
<%--</ul>--%>
<%--</p>--%>
<%--<form method="get">--%>
<%--<div>--%>
<%--<select class="form-control">--%>
<%--<option>Открытые сделки</option>--%>
<%--<option>Только мои сделки</option>--%>
<%--<option>Успешно завершенные</option>--%>
<%--<option>Нереализованные сделки</option>--%>
<%--<option>Сделки без задач</option>--%>
<%--<option>Сделки c просроченными задачами</option>--%>
<%--<option>Удаленные</option>--%>
<%--</select>--%>
<%--</div>--%>
<%--<div>--%>
<%--<h4>Когда</h4>--%>
<%--<select class="form-control">--%>
<%--<option> за все время</option>--%>
<%--<option>за сегодня</option>--%>
<%--<option>за 3 дня</option>--%>
<%--<option>за неделю</option>--%>
<%--<option>за месяц</option>--%>
<%--<option>за квартал</option>--%>
<%--</select>--%>
<%--<div class="form-group">--%>
<%--<div class='input-group date' id='datetimepicker2'>--%>
<%--<input type='text' class="form-control"/>--%>
<%--<span class="input-group-addon">--%>
<%--<span class="glyphicon glyphicon-calendar">--%>
<%--</span>--%>
<%--</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="control-group">--%>
<%--<h4>Этапы</h4>--%>
<%--<div class="controls">--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> без сделок--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> без открытых сделок--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> первичный контакт--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> переговоры--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> принимают решения--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> согласованые договора--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> успешно реализованы--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> закрыто--%>
<%--</label>--%>
<%--<label class="checkbox">--%>
<%--<input type="checkbox" value=""> не реализовано--%>
<%--</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div>--%>
<%--<h4>Менеджеры:</h4>--%>
<%--<select class="form-control">--%>

<%--</select>--%>
<%--</div>--%>
<%--<div>--%>
<%--<h4>Задачи</h4>--%>
<%--<select class="form-control">--%>
<%--<option>не учитывать</option>--%>
<%--<option>на сегодня</option>--%>
<%--<option>в этом месяце</option>--%>
<%--<option>в этом квартале</option>--%>
<%--<option>нет задач</option>--%>
<%--<option>просрочены</option>--%>
<%--</select>--%>
<%--</div>--%>
<%--<div>--%>
<%--<h4>Теги:</h4>--%>
<%--<select class="form-control">--%>

<%--</select>--%>
<%--</div>--%>
<%--<div class="form-actions">--%>
<%--<p>--%>
<%--<button type="submit" class="btn btn-success btn-small" style=" float: right;">Применить--%>
<%--</button>--%>
<%--<button type="reset" class="btn btn-success btn-small" style=" float: left;">Очистить</button>--%>
<%--</p>--%>
<%--</div>--%>
<%--</form>--%>
<%--</aside>--%>
<%--<div class="col-xs-9 col-sm-9 col-md-9">--%>

<%--</div>--%>
<%--</div>--%>

<%--</div>--%>


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
