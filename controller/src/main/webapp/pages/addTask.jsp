<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Shevchenko
  Date: 18.11.2015
  Time: 2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
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

    <script src="https://code.jquery.com/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
    <script src="pages/resources/js/addTask.js" type="text/javascript"></script>

    <%--<link href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/bootstrap.css" rel="stylesheet"/>--%>
    <link href="pages/resources/css/addTask.css" rel="stylesheet"/>
    <link href="http://code.jquery.com/ui/1.11.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">


</head>
<body onload="initPageAddTask()">
<jsp:include page="/header"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-1 main" id="selEl">
            <div class="panel-heading">
                <h3 class="panel-title">Создание задачи</h3>
            </div>
            <div class="panel-body">
            <h4>Период</h4>
            <select id="periodTask">
                <option value="0">Сегодня</option>
                <option value="1">Завтра</option>
                <option value="2">Следующая неделя</option>
                <option value="3">Следующий месяц</option>
                <option value="4">Следующий год</option>
            </select>

            <h4>Дата</h4>
            <div id="datepicker" >
            </div>

            <h4>Время</h4>
            <select id="timeTask">
                <option value="23:59">Весь день</option>
                <option>00:00</option>
                <option>00:30</option>
                <option>01:00</option>
                <option>01:30</option>
                <option>02:00</option>
                <option>02:30</option>
                <option>03:00</option>
                <option>03:30</option>
                <option>04:00</option>
                <option>04:30</option>
                <option>05:00</option>
                <option>05:30</option>
                <option>06:00</option>
                <option>06:30</option>
                <option>07:00</option>
                <option>07:30</option>
                <option>08:00</option>
                <option>08:30</option>
                <option>09:00</option>
                <option>09:30</option>
                <option>10:00</option>
                <option>10:30</option>
                <option>11:00</option>
                <option>11:30</option>
                <option>12:00</option>
                <option>12:30</option>
                <option>13:00</option>
                <option>13:30</option>
                <option>14:00</option>
                <option>14:30</option>
                <option>15:00</option>
                <option>15:30</option>
                <option>16:00</option>
                <option>16:30</option>
                <option>17:00</option>
                <option>17:30</option>
                <option>18:00</option>
                <option>18:30</option>
                <option>19:00</option>
                <option>19:30</option>
                <option>20:00</option>
                <option>20:30</option>
                <option>21:00</option>
                <option>21:30</option>
                <option>22:00</option>
                <option>22:30</option>
                <option>23:00</option>
                <option>23:30</option>
            </select>

            <h4>Название</h4>
            <!--List of contacts, deals and companies from server-->
            <select id="selNameTask">
                <option disabled>Контакт</option>>
                <c:forEach var="contact" items="${listContact}">
                    <option value="${contact.id}" name="contact" obj="${contact}"><c:out value="${contact.name}"/></option>
                </c:forEach>
                <option disabled>Сделка</option>>
                <c:forEach var="deal" items="${listDeal}">
                    <option value="${deal.id}" name="deal"><c:out value="${deal.name}"/></option>
                </c:forEach>
                <option disabled>Компания</option>>
                <c:forEach var="company" items="${listCompany}">
                    <option value="${company.id}" name="company"><c:out value="${company.name}"/></option>
                </c:forEach>
            </select>


            <h4>Ответственный</h4>
            <!--List of responsiblies from server-->
            <select id="selResponsibleTask">
                <c:forEach var="user" items="${listUser}">
                    <option value="${user.id}"><c:out value="${user.name} "/></option>
                </c:forEach>
            </select>

            <h4>Тип</h4>
            <select id="selTypeTask">
                <c:forEach var="taskType" items="${listTaskType}">
                    <option value="${taskType.id}"><c:out value="${taskType.name} "/></option>
                </c:forEach>
            </select>
            </div>
            <div id="commentBlock" style="float:left">

            <div id="commentEl">
        <%--<div class="col-sm-9 col-sm-offset-3 col-md-4 col-md-offset-2 main" id="commentEl">--%>
            <h4>Комментарий</h4>
            <textarea id="commentTask"></textarea>
            </BR>
            <form id="formAddTask" class="pull-right" action="/addTask" method="POST">
                <input type="hidden" name="finishDate"/>
                <input type="hidden" name="nameTask"/>
                <input type="hidden" name="typeNameTask"/>
                <input type="hidden" name="objNameTask"/>
                <input type="hidden" name="responsibleTask"/>
                <input type="hidden" name="typeTask"/>
                <input type="hidden" name="commentTask"/>
                <input type="button" name="butCancelTask" value="Отменить"/>
                <input id="butAddTask" type="button" name="butAddTask" value="Добавить"/>
            </form>
            </div>
        </div>
        </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>--%>
<%--<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>--%>
<%--<script src="../../dist/js/bootstrap.min.js"></script>--%>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<%--<script src="../../assets/js/vendor/holder.min.js"></script>--%>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<%--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>--%>

</body>
</html>
<script>
    /*//event of click addTask-button
    $("#butAddTask").click(function(){
        addTask("/index.jsp");
    });*/

</script>
