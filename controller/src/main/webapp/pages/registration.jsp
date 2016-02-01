<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>CRM-Prometheus</title>
    <link href="<c:url value="/pages/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/pages/resources/css/registration.css"/>" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>"/>

    <script src="<c:url value="https://code.jquery.com/jquery-2.1.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="http://code.jquery.com/ui/1.10.4/jquery-ui.js"/>" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>"/>
</head>
<body>
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-card">
            <div class="login-form">
                <form class="form-horizontal" action="/registr" method="post">
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
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="Введите имя" required>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="email"><i class="fa fa-envelope"></i></label>
                        <input type="text" class="form-control" id="email" name="email"
                               placeholder="Введите емейл" required>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Введите пароль" required>
                    </div>
                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

                    <div class="form-actions text-center">
                        <div class="col-md-6">
                            <input type="submit" name="registr"
                                   class="btn  btn-primary" value="Зарегистрироваться">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>




