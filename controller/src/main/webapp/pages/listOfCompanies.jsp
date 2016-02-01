<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 07.01.2016
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Список компаний</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRM System</title>
        <link rel="stylesheet" href="pages/resources/css/bootstrap.css">
    </head>
    <style>
        .link {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<jsp:include page="/header"/>
<div class="container-fluid" style="padding-top:50px;">
    <div class="row">
        <jsp:include page="menu.jsp"/>

        <div class="col-md-10 main">
            <h3>
                <b>Список компаний</b>
                <a href="addCompany" class="btn btn-info pull-right" id="company-add-button" title="Добавить Компанию">Добавить
                    компанию</a>
            </h3>
            <table class="table table-hover table-bordered table-striped" border="1" cellpadding="8" cellspacing="0">
                <thead style="background-color: #dff0d8;">
                <tr>
                    <th>Наименование</th>
                    <th>Телефон</th>
                    <th>Email</th>
                </tr>
                </thead>
                <tbody>
                <%--<tr>--%>
                <%--<td>--%>
                <%----%>
                <%--</td>--%>
                <%--</tr>--%>
                <c:forEach items="${companies}" var="company">
                    <tr>
                        <td>
                                <%--<option value="${company.getId()}">${company.getName()}</option>--%>
                            <a class="link" href="editCompany?id=${company.getId()}" name="edit-company-id"
                               id="edit-company-id" title="Редактировать компанию">${company.getName()}</a>
                        </td>
                        <td>
                            <c:forEach items="${company.getPhones()}" var="phone">
                                <option>${company.getPhones().iterator().next().getValue()}</option>
                            </c:forEach>
                        </td>
                        <td>
                            <option>${company.getEmail()}</option>
                        </td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
