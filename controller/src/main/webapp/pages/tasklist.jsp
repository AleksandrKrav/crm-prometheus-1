<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <title>CRM-Prometheus</title>
    <!-- Bootstrap core CSS -->
    <link href="pages/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="pages/resources/css/prometheus.css" rel="stylesheet">
</head>

<body>
<jsp:include page="/header"/>

<div class="container-fluid">
    <jsp:include page="menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="page-header">List of Task</h2>

            <div class="col-md-11">

                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default">To-do linet</button>
                    <button type="button" class="btn btn-default">День</button>
                    <button type="button" class="btn btn-default">Неделя</button>
                    <button type="button" class="btn btn-default">Месяц</button>
                    <button type="button" class="btn btn-active">Список</button>
                </div>


                <div style="float: right;">
                    <a href="/pages/taskadd.jsp">
                        <button type="button" class="btn btn-primary">Добавить задачу</button>
                    </a>
                </div>

            </div>

            <div class="container">
                <div class="row">
                    <div class="col-md-11 main">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Список задач</h3>
                            </div>
                            <div class="panel-body">

                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>Дата исполнения/Время/Ответственный</th>
                                        <th>Контакт или сделка</th>
                                        <th>Тип/Текст задачи</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${tasks}" var="task">
                                        <tr>
                                            <td>${task.getFinishDate()} / ... / ${task.getOwner().getName()}</td>
                                            <td>${task.getContact().getName()} / ${task.getDeal().getName()}</td>
                                            <td>${task.getType().getName()} / ${task.getDescription()}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>


                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../dist/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="../../assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
