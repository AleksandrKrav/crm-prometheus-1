<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/index.jsp">CRM-Prometheus</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <li class="user-name">Добро пожаловать, <c:out value="${user_name}"/></li>
                <%--<li class="user-name">Добро пожаловать, <strong>${cookie['user'].value}</strong></li>--%>
                <li><a href="#">Профиль пользователя</a></li>
                <li id="logoutUser"><a href="<c:url value="/logout"/>">Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
