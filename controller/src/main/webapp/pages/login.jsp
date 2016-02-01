<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>CRM-Prometheus</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/registration.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>

    <script src="https://code.jquery.com/jquery-2.1.4.js" type="text/javascript"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
</head>
<body>
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-card">
            <div class="login-form">

                <form action="/j_spring_security_check" method="post" class="form-horizontal">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Invalid username and password.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-envelope"></i></label>
                        <input type="text" class="form-control" id="username" name="j_username"
                               placeholder="Enter Email" required>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                        <input type="password" class="form-control" id="password" name="j_password"
                               placeholder="Enter Password" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-actions">
                        <div class="col-md-6">
                            <input type="submit" name="enter"
                                   class="btn btn-block btn-primary" value="Войти">
                        </div>
                        <div class="col-md-6">
                            <a href="/pages/registration.jsp" id="registration-button" name="registration"
                               class="btn  btn-success" title="Зарегистрироваться">Зарегистрироваться</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>




