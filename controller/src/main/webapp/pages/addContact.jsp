<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="pages/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="pages/resources/css/prometheus.css" rel="stylesheet">
    <%--<link href="pages/resources/css/addTask.css" rel="stylesheet"/>--%>
    <link rel="stylesheet" href="pages/resources/css/bootstrap.css">
    <link rel="stylesheet" href="pages/resources/css/addCompany.css">
    <link rel="stylesheet" href="pages/resources/css/bootstrap-datetimepicker.min.css" />
    <link href="http://code.jquery.com/ui/1.11.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-2.1.4.js" type="text/javascript"></script>
    <script type="text/javascript" src="pages/resources/js/moment-with-locales.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
    <script src="pages/resources/js/addTask.js" type="text/javascript"></script>
    <script src="pages/resources/js/addContact.js" type="text/javascript"></script>
    <script src="pages/resources/js/addCompany.js" type="text/javascript"></script>
    <script src="pages/resources/js/addDeal.js" type="text/javascript"></script>
    <script type="text/javascript" src="pages/resources/js/bootstrap-datetimepicker.min.js"></script>

</head>
<body onload="initPageAddTask()">

<jsp:include page="/header"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-sm-2 col-md-10 main">
            <div class="panel contact-panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Создание контакта</h3>
                </div>
                <div class="panel-body">
                        <div class="col-md-6">
                            <fieldset class="field-contact">
                                <legend>Добавить контакт</legend>
                                <form class="form-horizontal" id="formAddContact" name="formAddContact" action="/addContact" method="POST" enctype="multipart/form-data">
                                <div class="form-group contact-name-group">
                                    <label for="nameContact" class="col-md-4 control-label">Имя</label>
                                    <div class="col-md-8">
                                        <input type="text" name="nameContact" class="form-control" id="nameContact"/>
                                    </div>
                                </div>
                                <div class="form-group contact-tag-group">
                                    <label for="tagContact" class="col-md-4 control-label">Тег</label>
                                    <div class="col-md-8">
                                        <input type="text" name="tagContact" class="form-control" id="tagContact"/>
                                    </div>
                                </div>
                                <div class="form-group contact-owner-group">
                                    <label for="selResponsibleContact" class="col-md-4 control-label">Ответственный</label>
                                    <div class="col-md-8">
                                        <select name="selResponsibleContact" class="form-control" id="selResponsibleContact">
                                            <c:forEach var="owner" items="${owners}">
                                                <option value="${owner.id}"><c:out value="${owner.name}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group contact-position-group">
                                    <label for="selPositionContact" class="col-md-4 control-label">Должность</label>
                                    <div class="col-md-8">
                                        <input type="text" name="selPositionContact" class="form-control" id="selPositionContact"/>
                                    </div>
                                </div>
                                <div class="form-group contact-company-group">
                                    <label for="selCompanyContact" class="col-md-4 control-label">Компания</label>
                                    <div class="col-md-8">
                                        <select name="selCompanyContact" class="form-control" id="selCompanyContact">
                                            <c:forEach var="company" items="${companies}">
                                                <option value="${company.id}"><c:out value="${company.name}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group contact-phone-number-group">
                                    <div class="col-md-4">
                                        <select name="selPhoneType" class="form-control" id="selPhoneType">
                                            <c:forEach var="phoneType" items="${phoneTypes}">
                                                <option value="${phoneType.id}"><c:out value="${phoneType.name}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-8">
                                        <input type="text" name="phoneNum" class="form-control" id="phoneNum"/>
                                    </div>
                                </div>
                                <div class="form-group contact-email-group">
                                    <label for="emailContact" class="col-md-4 control-label">Email</label>
                                    <div class="col-md-8">
                                        <input type="text" name="emailContact" class="form-control" id="emailContact"/>
                                    </div>
                                </div>
                                <div class="form-group contact-skype-group">
                                    <label for="skypeContact" class="col-md-4 control-label">Skype</label>
                                    <div class="col-md-8">
                                        <input type="text" name="skypeContact" class="form-control" id="skypeContact"/>
                                    </div>
                                </div>
                                <div class="form-group contact-comment-group">
                                    <label for="commentContact" class="col-md-4 control-label">Примечание</label>
                                    <div class="col-md-8">
                                        <textarea name="commentContact" class="form-control" id="commentContact"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label">Добавить файлы</label>
                                    <div class="col-md-8">
                                        <input id="files-contact" type="file" class="file" name="files-contact" multiple="multiple">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="text-center">
                                        <input id="saveContact" name="butSaveContact" type="button" value="Сохранить"
                                        class="btn btn-primary">
                                        <input id="contact-cancel-button" type="reset" value="Отмена"
                                               class="btn btn-default">
                                    </div>
                                </div>
                                </form>
                            </fieldset>

                            <fieldset class="field-company">
                                <legend>Добавить компанию</legend>
                                <form class="form-horizontal" id="company-add-form" action="/addCompany" method="POST" enctype="multipart/form-data">
                                    <div class="form-group company-new-group">
                                        <label for="company-new" class="col-md-4 control-label">Новая компания</label>
                                        <div class="col-md-8">
                                            <select id="company-new" class="form-control" name="company-new">
                                                <option value=""></option>
                                                <c:forEach items="${companies}" var="company">
                                                    <option value="${company.getId()}">${company.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group company-name-group">
                                        <label for="company-name" class="col-md-4 control-label">Название компании</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="company-name" name="company-name"
                                                   value size="30" autofocus >
                                        </div>
                                    </div>
                                    <div class="form-group company-owner-group">
                                        <label for="company-owner" class="col-md-4 control-label">Ответственный</label>
                                        <div class="col-md-8">
                                            <select id="company-owner" class="form-control" name="company-owner">
                                                <c:forEach items="${owners}" var="owner">
                                                    <option value="${owner.getId()}">${owner.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group company-address-group">
                                        <label for="company-address" class="col-md-4 control-label">Адрес</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="company-address" name="company-address"
                                                   value size="30" autofocus >
                                        </div>
                                    </div>
                                    <div class="form-group company-phone-number-group">
                                        <div class="col-md-4">
                                            <select id="company-phone-type" class="form-control" name="company-phone-type">
                                                <c:forEach items="${phoneTypes}" var="phoneType">
                                                    <option value = ${phoneType.getId()}>${phoneType.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="company-phone-number" name="company-phone-number"
                                                   value size="30" autofocus >
                                        </div>
                                    </div>
                                    <div class="form-group company-email-group">
                                        <label for="company-email" class="col-md-4 control-label">Email</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="company-email" name="company-email"
                                                   size="30" autofocus value  >
                                        </div>
                                    </div>
                                    <div class="form-group company-web-group">
                                        <label for="company-web" class="col-md-4 control-label">Web</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="company-web" name="company-web"
                                                   value size="30" autofocus  >
                                        </div>
                                    </div>
                                    <div class="form-group company-comment-group">
                                        <label for="company-comment" class="col-md-4 control-label">Примечание к компании</label>
                                        <div class="col-md-8">
                                            <textarea id="company-comment" class="form-control" name="company-comment"
                                                      rows="3" cols="30">
                                            </textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="files" class="col-md-4 control-label file-label">Добавить файлы</label>
                                        <div class="col-md-8">
                                            <input id="files" type="file" class="file" name="files" multiple="multiple">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="text-center">
                                            <input id="company-id-button" name="company-submit" type="button" value="Сохранить"
                                                   class="btn btn-primary">
                                            <input id="company-cancel-button" type="reset" value="Отмена"
                                                   class="btn btn-default">
                                        </div>
                                    </div>
                                </form>
                            </fieldset>
                        </div>

                        <div class="col-md-6">
                            <fieldset class="field-task">
                                <legend>Запланировать действие</legend>
                                <div class="form-group task-period-group">
                                    <label for="periodTask" class="col-md-4 control-label">Период</label>
                                    <div class="col-md-4">
                                        <select name="periodTask" class="form-control" id="periodTask">
                                            <option value="0">Сегодня</option>
                                            <option value="1">Завтра</option>
                                            <option value="2">Следующая неделя</option>
                                            <option value="3">Следующий месяц</option>
                                            <option value="4">Следующий год</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <select name="timeTask" id="timeTask" class="form-control">
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
                                    </div>
                                </div>

                                <div class="form-group task-date-group">
                                    <label for="periodTask" class="col-md-4 control-label">Период</label>
                                    <div class="col-md-8">
                                        <div id="datepicker"/>
                                    </div>
                                </div>

                                <div class="form-group task-owner-group">
                                    <label for="selResponsibleTask" class="col-md-4 control-label">Ответственный</label>
                                    <div class="col-md-8">
                                        <select name="selResponsibleTask" class="form-control" id="selResponsibleTask">
                                            <c:forEach var="owner" items="${owners}">
                                                <option value="${owner.id}"><c:out value="${owner.name}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group task-type-group">
                                    <label for="selTypeTask" class="col-md-4 control-label">Тип задачи</label>
                                    <div class="col-md-8">
                                        <select name="selTypeTask" class="form-control" id="selTypeTask">
                                            <c:forEach var="taskType" items="${listTaskType}">
                                                <option value="${taskType.id}"><c:out value="${taskType.name} "/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group task-comment-group">
                                    <label for="commentTask" class="col-md-4 control-label">Текст задачи</label>
                                    <div class="col-md-8">
                                        <textarea name="commentTask" class="form-control" id="commentTask"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="text-center">
                                        <form id="formAddTask" class="pull-right" action="/addTask" method="POST">
                                            <input type="hidden" name="finishDate"/>
                                            <input type="hidden" name="nameTask"/>
                                            <input type="hidden" name="typeNameTask"/>
                                            <input type="hidden" name="objNameTask"/>
                                            <input type="hidden" name="responsibleTask"/>
                                            <input type="hidden" name="typeTask"/>
                                            <input type="hidden" name="commentTask"/>
                                            <input id="butAddTask" name="butAddTask" type="button" value="Сохранить"
                                                   class="btn btn-primary">
                                            <input id="task-cancel-button" type="reset" value="Отмена"
                                                   class="btn btn-default">
                                        </form>
                                    </div>
                                </div>
                            </fieldset>

                            <fieldset class="field-deal">
                                <legend>Добавить сделку</legend>
                                <form accept-charset="UTF-8" class="form-horizontal" id="deal-add-form"
                                      action="/add_deal"
                                      enctype="multipart/form-data"
                                      method="post">
                                    <div class="col-md-12">
                                        <div class="form-group deal-name-group">
                                            <label for="deal-name" class="col-md-4 control-label">Имя сделки</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" id="deal-name"
                                                       name="deal-name"
                                                       value size="30" autofocus>
                                            </div>
                                        </div>
                                        <div class="form-group deal-tag-group">
                                            <label for="deal-tag" class="col-md-4 control-label">Тег сделки</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" id="deal-tag"
                                                       name="deal-tag"
                                                       value size="30" autofocus>
                                            </div>
                                        </div>
                                        <div class="form-group deal-owner-group">
                                            <label for="deal-owner"
                                                   class="col-md-4 control-label">Ответственный</label>
                                            <div class="col-md-8">
                                                <select id="deal-owner" class="form-control"
                                                        name="deal-owner">
                                                    <c:forEach items="${owners}" var="owner">
                                                        <option value="${owner.getId()}">${owner.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group deal-budget-group">
                                            <label for="deal-budget" class="col-md-4 control-label">Бюджет</label>
                                            <div class="col-md-4">
                                                <input type="text" class="form-control" id="deal-budget"
                                                       name="deal-budget"
                                                       value size="30" autofocus>
                                            </div>
                                            <div class="col-md-4">
                                                <select id="selCurrency" class="form-control" name="deal-currency">
                                                    <c:forEach var="currency" items="${listCurrency}">
                                                        <option value="${currency.id}"><c:out value="${currency.name}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group deal-status-group">
                                            <label for="deal-status"
                                                   class="col-md-4 control-label">Этап</label>
                                            <div class="col-md-8">
                                                <select id="deal-status" class="form-control"
                                                        name="deal-status">
                                                    <c:forEach items="${statuses}" var="status">
                                                        <option value="${status.getId()}">${status.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group company-name-group">
                                            <label for="company-name-deal"
                                                   class="col-md-4 control-label">Название компании</label>
                                            <div class="col-md-8">
                                                <select id="company-name-deal" class="form-control"
                                                        name="company-name-deal">
                                                    <c:forEach items="${companies}" var="company">
                                                        <option value="${company.getId()}">${company.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group deal-comment-group">
                                            <label for="deal-comment"
                                                   class="col-md-4 control-label">Примечания</label>
                                            <div class="col-md-8">
                                                <textarea id="deal-comment" class="form-control"
                                                          name="deal-comment"
                                                          rows="3" cols="30">
                                                </textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-4"></div>
                                            <div class="col-md-8">
                                                <input id="deal-add-button" name="deal-add"
                                                       type="button" value="Сохранить"
                                                       class="btn btn-primary">
                                                <input id="deal-cancel-button" type="reset"
                                                       value="Отмена" class="btn btn-default">
                                            </div>
                                        </div>
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
