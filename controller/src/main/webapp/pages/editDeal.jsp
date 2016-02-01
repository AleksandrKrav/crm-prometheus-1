<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 13.01.2016
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Deal</title>
    <link rel="stylesheet" href="pages/resources/css/bootstrap.css">
    <link rel="stylesheet" href="pages/resources/css/addCompany.css">
    <link rel="stylesheet" href="pages/resources/css/bootstrap-datetimepicker.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="pages/resources/js/moment-with-locales.min.js"></script>
    <script type="text/javascript" src="pages/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="pages/resources/js/bootstrap-datetimepicker.min.js"></script>
    <script src="pages/resources/js/addDeal.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<jsp:include page="/header"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-md-10 main">
            <div class="panel company-panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Редактирование сделки</h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" id="deal-edit-form" action="/edit_deal" method="POST"
                          enctype="multipart/form-data">
                        <div class="col-md-4">
                            <fieldset class="field-deal">
                                <legend>Редактирование сделки</legend>
                            </fieldset>
                            <div class="form-group deal-name-group">
                                <label for="deal-name" class="col-md-4 control-label">Название сделки</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="deal-name"
                                           name="deal-name"
                                           value="${deal.getName()}" size="30" autofocus>
                                </div>
                            </div>
                            <div class="form-group deal-tag-group">
                                <label for="deal-tag" class="col-md-4 control-label">Теги сделки</label>
                                <div class="col-md-8">
                                    <textarea id="deal-tag" class="form-control"
                                              name="deal-tag"
                                              rows="3" cols="30">
                                                    <c:if test="${deal.tags.size() > 0}"> ${deal.tags.toArray()[0].getName()}</c:if>
                                                </textarea>
                                </div>
                            </div>
                            <div class="form-group deal-owner-group">
                                <label for="deal-owner"
                                       class="col-md-4 control-label">Ответственный</label>
                                <div class="col-md-8">
                                    <select id="deal-owner" class="form-control"
                                            name="deal-owner">
                                        <c:forEach items="${owners}" var="owner">
                                            <option value="${owner.getId()}"
                                                    <c:if test="${owner.id eq deal.owner.getId()}">SELECTED</c:if>>${owner.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group deal-budget-group">
                                <label for="deal-budget" class="col-md-4 control-label">Бюджет</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" id="deal-budget"
                                           name="deal-budget"
                                           value="${deal.getBudget()}" size="30" autofocus>
                                </div>
                                <div class="col-md-4">
                                    <label class="control-label">${currency.getName()}</label>
                                </div>
                            </div>
                            <div class="form-group deal-status-group">
                                <label for="deal-status"
                                       class="col-md-4 control-label">Этап</label>
                                <div class="col-md-8">
                                    <select id="deal-status" class="form-control"
                                            name="deal-status">
                                        <c:forEach items="${statuses}" var="status">
                                            <option value="${status.getId()}"
                                                    <c:if test="${status.id eq deal.status.getId()}">SELECTED</c:if>>${status.getName()}</option>
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
                                                    <c:if test="${deal.comments.size() > 0}">${deal.comments.toArray()[0].getComment()}</c:if>
                                                </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="files" class="col-md-4 control-label file-label">Обновить файлы</label>
                                <div class="col-md-8">
                                    <label>
                                        ${deal.files.toArray()[0].getName()}
                                        <input id="files" value="${deal.files.toArray()[0].getName()}" type="file"
                                               class="file" name="files" multiple="multiple">
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-4"></div>
                                <div class="col-md-8">
                                    <input id="deal-edit-button" name="deal-edit"
                                           type="submit" value="Обновить"
                                           class="btn btn-primary">
                                    <input id="deal-delete-button" naem="deal-delete" type="submit"
                                           value="Удалить" class="btn btn-danger">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <fieldset>
                                <legend>Запланировать действие</legend>
                            </fieldset>

                            <div class="form-group task-period-type-group">
                                <label for="task-period-type"
                                       class="col-md-4 control-label">Когда </label>
                                <div class="col-md-8">
                                    <select id="task-period-type" class="form-control"
                                            name="task-period-type">
                                        <option value="1">Сегодня</option>
                                        <option value="2">Завтра</option>
                                        <option value="3">Следующая неделя</option>
                                        <option value="4">Следующий месяц</option>
                                        <option value="5">Следующий год</option>
                                        <option value="6">Выбрать дату</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group task-date-group">
                                <label for="task-date" class="col-md-4 control-label">Дата</label>
                                <div class="col-md-8" id="datetimepicker1">
                                    <div class="input-group date">
                                        <input id="task-date" type="text" class="form-control"
                                               name="task-date"/>
    						<span class="input-group-addon">
      						<span class="glyphicon glyphicon-calendar"></span>
    					</span>

                                    </div>
                                </div>
                            </div>
                            <div class="form-group task-time-group">
                                <label for="task-period-time"
                                       class="col-md-4 control-label">Время</label>
                                <div class="col-md-8">
                                    <select id="task-period-time" class="form-control"
                                            name="task-period-time">
                                        <option value="allDay">Весь день</option>
                                        <option value="00:00">00:00</option>
                                        <option value="00:30">00:30</option>
                                        <option value="01:00">01:00</option>
                                        <option value="01:30">01:30</option>
                                        <option value="02:00">02:00</option>
                                        <option value="02:30">02:30</option>
                                        <option value="03:00">03:00</option>
                                        <option value="03:30">03:30</option>
                                        <option value="04:00">04:00</option>
                                        <option value="04:30">04:30</option>
                                        <option value="05:00">05:00</option>
                                        <option value="05:30">05:30</option>
                                        <option value="06:00">06:00</option>
                                        <option value="06:30">06:30</option>
                                        <option value="07:00">07:00</option>
                                        <option value="07:30">07:30</option>
                                        <option value="08:00">08:00</option>
                                        <option value="08:30">08:30</option>
                                        <option value="09:00">09:00</option>
                                        <option value="09:30">09:30</option>
                                        <option value="10:00">10:00</option>
                                        <option value="10:30">10:30</option>
                                        <option value="11:00">11:00</option>
                                        <option value="11:30">11:30</option>
                                        <option value="12:00">12:00</option>
                                        <option value="12:30">12:30</option>
                                        <option value="13:00">13:00</option>
                                        <option value="13:30">13:30</option>
                                        <option value="14:00">14:00</option>
                                        <option value="14:30">14:30</option>
                                        <option value="15:00">15:00</option>
                                        <option value="15:30">15:30</option>
                                        <option value="16:00">16:00</option>
                                        <option value="16:30">16:30</option>
                                        <option value="17:00">17:00</option>
                                        <option value="17:30">17:30</option>
                                        <option value="18:00">18:00</option>
                                        <option value="18:30">18:30</option>
                                        <option value="19:00">19:00</option>
                                        <option value="19:30">19:30</option>
                                        <option value="20:00">20:00</option>
                                        <option value="20:30">20:30</option>
                                        <option value="21:00">21:00</option>
                                        <option value="21:30">21:30</option>
                                        <option value="22:00">22:00</option>
                                        <option value="22:30">22:30</option>
                                        <option value="23:00">23:00</option>
                                        <option value="23:30">23:30</option>
                                    </select></div>
                            </div>
                            <div class="form-group task-owner-group">
                                <label for="task-owner"
                                       class="col-md-4 control-label">Ответственный</label>
                                <div class="col-md-8">
                                    <select id="task-owner" class="form-control" name="task-owner">
                                        <c:forEach items="${owners}" var="owner">
                                            <option value="${owner.getId()}">${owner.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group task-type-group">
                                <label for="task-type" class="col-md-4 control-label">Тип</label>
                                <div class="col-md-8">
                                    <select id="task-type" class="form-control" name="task-type">
                                        <c:forEach items="${taskTypes}" var="taskType">
                                            <option value="${taskType.getId()}">${taskType.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group task-description-group">
                                <label for="task-description" class="col-md-4 control-label">Описание
                                    задачи</label>
                                <div class="col-md-8">
						<textarea id="task-description" class="form-control" name="task-description"
                                  rows="3" cols="30">
						</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="text-center">
                                    <input id="task-id-button" name="task-submit" type="submit"
                                           value="Сохранить"
                                           class="btn btn-primary">
                                    <input id="task-cancel-button" type="reset" value="Отмена"
                                           class="btn btn-default">
                                </div>
                            </div>
                        </div>


                        <div class="col-md-4">
                            <fieldset>
                                <legend>Контакт</legend>
                            </fieldset>
                            <div class="form-group deal-contact-group">
                                <div class="tabbable">
                                    <ul class="nav nav-tabs">
                                        <c:forEach items="${contacts}" var="contact" varStatus="status">
                                            <li><a href="#tab${status.index}" data-toggle="tab">${contact.getName()}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <c:forEach items="${contacts}" var="contact" varStatus="status">
                                        <div class="tab-content">
                                            <div class="tab-pane" id="tab${status.index}">
                                                <div class="form-group contact-name-group">
                                                    <label for="contact-name"
                                                           class="col-md-4 control-label">Имя Фамилия</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="contact-name"
                                                               name="contact-name"
                                                               value="${contact.getName()}" size="30" autofocus>
                                                    </div>
                                                </div>
                                                <div class="form-group contact-owner-group">
                                                    <label for="contact_owner"
                                                           class="col-md-4 control-label">Ответственный</label>
                                                    <div class="col-md-8">
                                                        <div class="col-md-8">
                                                            <input type="text" class="form-control" id="contact_owner"
                                                                   name="contact_owner"
                                                                   value="${contact.owner.getName()}" size="30"
                                                                   autofocus>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group contact-company-group">
                                                    <label for="contact-company"
                                                           class="col-md-4 control-label">Компания</label>
                                                    <div class="col-md-8">
                                                        <div class="col-md-8">
                                                            <input type="text" class="form-control" id="contact-company"
                                                                   name="contact-company"
                                                                   value="${contact.company.getName()}" size="30"
                                                                   autofocus>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group contact_position-group">
                                                    <label for="contact-position"
                                                           class="col-md-4 control-label">Должность</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" id="contact-position"
                                                               name="contact-position"
                                                               value="${contact.position.getName()}" size="30"
                                                               autofocus>
                                                    </div>
                                                </div>
                                                <div class="form-group contact-phone-number-group">
                                                    <div class="col-md-12">
                                                        <select id="contact-phone-type"
                                                                class="form-control"
                                                                name="contact-phone-type">
                                                            <c:forEach items="${contact.phones}"
                                                                       var="phone" varStatus="status">
                                                                <c:set var="phoneId" value="${status.index}"/>
                                                                <option value="${phone.getId()}">
                                                                        ${phone.phoneType.getName()}
                                                                    <input type="text" class="form-control"
                                                                           id="contact-phone-number"
                                                                           name="contact-phone-number"
                                                                           value="${phone.getValue()}" size="30"
                                                                           autofocus>
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group contact-email-group">
                                                    <label for="contact-email"
                                                           class="col-md-4 control-label">E-mail</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control"
                                                               id="contact-email"
                                                               name="contact-email"
                                                               value="${contact.getEmail()}" size="30" autofocus>
                                                    </div>
                                                </div>
                                                <div class="form-group contact-skype-group">
                                                    <label for="contact-skype"
                                                           class="col-md-4 control-label">Skype</label>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control"
                                                               id="contact-skype"
                                                               name="contact_skype"
                                                               value="${contact.getSkype()}" size="30"
                                                               autofocus>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-3">
                                    <input id="contact-add-button" name="contact-add-button"
                                           type="button"
                                           value="Добавить"
                                           class="btn btn-primary" data-toggle="modal"
                                           data-target="#myModal2">
                                    <div class="modal fade " id="myModal2">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <div class="modal-header">
                                                        <button class="close" type="button"
                                                                data-dismiss="modal">×
                                                        </button>
                                                        <h3 id="myModalLabel2">Добавление контакта</h3>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="form-group contact-name-group">
                                                            <label for="contact_name"
                                                                   class="col-md-4 control-label">Имя
                                                                Фамилия</label>
                                                            <div class="col-md-8">
                                                                <input type="text" class="form-control"
                                                                       id="contact_name"
                                                                       name="contact_name"
                                                                       size="30" autofocus>
                                                            </div>
                                                        </div>
                                                        <div class="form-group contact-owner-group">
                                                            <label for="contact-owner"
                                                                   class="col-md-4 control-label">Ответственный</label>
                                                            <div class="col-md-8">
                                                                <select id="contact-owner"
                                                                        class="form-control"
                                                                        name="contact-owner">
                                                                    <c:forEach items="${owners}"
                                                                               var="owner">
                                                                        <option value="${owner.getId()}">${owner.getName()}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group contact-company-group">
                                                            <label for="contact_company"
                                                                   class="col-md-4 control-label">Компания</label>
                                                            <div class="col-md-8">
                                                                <select id="contact_company"
                                                                        class="form-control"
                                                                        name="contact_company">
                                                                    <c:forEach items="${companies}"
                                                                               var="company">
                                                                        <option value="${company.getId()}">${company.getName()}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group contact_position-group">
                                                            <label for="contact_position"
                                                                   class="col-md-4 control-label">Должность</label>
                                                            <div class="col-md-8">
                                                                <select id="contact_position"
                                                                        class="form-control"
                                                                        name="contact_position">
                                                                    <c:forEach items="${positions}"
                                                                               var="position">
                                                                        <option value= ${position.getId()}>${position.getName()}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group contact-phone-number-group">
                                                            <div class="col-md-4">
                                                                <select id="contact_phone-type"
                                                                        class="form-control"
                                                                        name="contact_phone-type">
                                                                    <c:forEach items="${phoneTypes}"
                                                                               var="phoneType">
                                                                        <option value= ${phoneType.getId()}>${phoneType.getName()}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <input type="text" class="form-control"
                                                                       id="contact_phone-number"
                                                                       name="contact_phone-number"
                                                                       value size="30" autofocus>
                                                            </div>
                                                        </div>
                                                        <div class="form-group contact-email-group">
                                                            <label for="contact_email"
                                                                   class="col-md-4 control-label">E-mail</label>
                                                            <div class="col-md-8">
                                                                <input type="text" class="form-control"
                                                                       id="contact_email"
                                                                       name="contact_email"
                                                                       value size="30" autofocus>
                                                            </div>
                                                        </div>
                                                        <div class="form-group contact-skype-group">
                                                            <label for="contact_skype"
                                                                   class="col-md-4 control-label">Skype</label>
                                                            <div class="col-md-8">
                                                                <input type="text" class="form-control"
                                                                       id="contact_skype"
                                                                       name="contact_skype"
                                                                       value size="30"
                                                                       autofocus>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="modal-footer">
                                                            <input id="contact-add" name="contact-add"
                                                                   type="submit"
                                                                   value="Сохранить"
                                                                   class="btn btn-primary">
                                                            <input id="contact-cancel" type="reset"
                                                                   value="Отмена"
                                                                   class="btn btn-default"
                                                                   data-dismiss="modal">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <fieldset>
                                <legend>Компания</legend>
                            </fieldset>
                            <div class="form-group company-name-group">
                                <label for="company_name"
                                       class="col-md-4 control-label">Название компании</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"
                                           id="company_name"
                                           name="company_name"
                                           value="${company.getName()}"
                                           size="30" autofocus>
                                    <%--<select id="company-name" class="form-control"--%>
                                    <%--name="company-name">--%>
                                    <%--<c:forEach items="${companies}" var="company">--%>
                                    <%--<option value="${company.getId()}">${company.getName()}</option>--%>
                                    <%--</c:forEach>--%>
                                    <%--</select>--%>
                                </div>
                            </div>
                            <div class="form-group company-phone-group">
                                <label for="company_phone"
                                       class="col-md-4 control-label">Телефон</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"
                                           id="company_phone"
                                           name="company_phone"
                                           value="${company.phones.toArray()[0].getValue()}"
                                           size="30" autofocus>
                                </div>
                            </div>
                            <div class="form-group company-email-group">
                                <label for="company_email"
                                       class="col-md-4 control-label">E-mail</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"
                                           id="company_email"
                                           name="company_email"
                                           value="${company.getEmail()}"
                                           size="30" autofocus>
                                </div>
                            </div>
                            <div class="form-group company-web-group">
                                <label for="company_web"
                                       class="col-md-4 control-label">Web</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"
                                           id="company_web"
                                           name="company_web"
                                           value="${company.getWeb()}"
                                           size="30" autofocus>
                                </div>
                            </div>
                            <div class="form-group company-address-group">
                                <label for="company_address"
                                       class="col-md-4 control-label">Address</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"
                                           id="company_address"
                                           name="company_address"
                                           value="${company.getAddress()}"
                                           size="30" autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <a class="btn btn-info pull-right" href="editCompany?id=${company.getId()}" name="company-edit-id"
                                   title="Редактировать компанию">Редактировать</a>
                            </div>
                        </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    $("#task-period-type").change(function () {
        changePeriodType();
    });
</script>

</body>
</html>
