<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
  <link rel="stylesheet" href="pages/resources/css/bootstrap.css">
  <link rel="stylesheet" href="pages/resources/css/addCompany.css">
  <link rel="stylesheet" href="pages/resources/css/bootstrap-datetimepicker.min.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script type="text/javascript" src="pages/resources/js/moment-with-locales.min.js"></script>
  <script type="text/javascript" src="pages/resources/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="pages/resources/js/bootstrap-datetimepicker.min.js"></script>
  <script src="pages/resources/js/addCompany.js"></script>
    <script src="resources/js/authUser.js" type="text/javascript"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Create company</title>
</head>
<body>
  <jsp:include page="/header"/>
<div class="container-fluid" style="padding-top:50px;">
  <div class="row">
    <jsp:include page="menu.jsp"/>
    <div class="col-md-10 main">
      <div class="panel company-panel panel-success">
        <div class="panel-heading">
          <h3 class="panel-title">Создание компании</h3>
        </div>
        <div class="panel-body">
          <form class="form-horizontal" id="company-add-form" action="/addCompany" method="POST" enctype="multipart/form-data">
            <div class="col-md-6">
              <fieldset class="field-company">
                <legend>Добавить компанию</legend>
                <div class="form-group company-new-group">
                  <label for="company-new" class="col-md-4 control-label">Новая компания</label>
                  <div class="col-md-8">
                    <select id="company-new" class="form-control" name="company-new">
                      <option value="${company.getName()}">${company.getName()}</option>
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
                    <input id="company-id-button" name="company-submit" type="submit" value="Сохранить"
                           class="btn btn-primary" disabled>
                    <input id="company-cancel-button" type="reset" value="Отмена"
                           class="btn btn-default">
                  </div>
                </div>
              </fieldset>
              <fieldset>
                <legend>Контакт</legend>
                <div class="form-group contact-group">
                  <label for="contact-name-list" class="col-md-4 control-label">Имя Фамилия(список)</label>
                  <div class="col-md-8">
                    <select id="contact-name-list" class="form-control" name="contact-name-list">
                      <c:forEach items="${contacts}" var="contact">
                        <option value="${contact.getId()}">${contact.getName()}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <div class="text-center">
                    <input id="contact-fix-button" name="contact-fix-submit" type="submit" value="Прикрепить контакт"
                           class="btn btn-primary" disabled>
                    <input id="contact-fix-cancel-button" type="reset" value="Отмена"
                           class="btn btn-default">
                  </div>
                </div>
                <div class="form-group contact-name-group">
                  <label for="contact-name" class="col-md-4 control-label">Имя Фамилия</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="contact-name" name="contact-name"
                           value size="30" autofocus >
                  </div>
                </div>
                <div class="form-group contact-position-group">
                  <label for="contact-position" class="col-md-4 control-label">Должность</label>
                  <div class="col-md-8">
                    <select id="contact-position" class="form-control" name="contact-position">
                      <c:forEach items="${positions}" var="position">
                        <option value="${position.getId()}">${position.getName()}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group contact-phone-number-group">
                  <div class="col-md-4">
                    <select id="contact-phone-type" class="form-control" name="contact-phone-type">
                      <c:forEach items="${phoneTypes}" var="phoneType">
                        <option value = ${phoneType.getId()}>${phoneType.getName()}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="contact-phone-number" name="contact-phone-number"
                           value size="30" autofocus >
                  </div>
                </div>
                <div class="form-group contact-email-group">
                  <label for="contact-email" class="col-md-4 control-label">Email</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="contact-email" name="contact-email"
                           value size="30" autofocus  >
                  </div>
                </div>
                <div class="form-group contact-skype-group">
                  <label for="contact-skype" class="col-md-4 control-label">Skype</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="contact-skype" name="contact-skype"
                           value size="30" autofocus  >
                  </div>
                </div>
                <div class="form-group">
                  <div class="text-center">
                    <input id="contact-id-button" name="contact-submit" type="submit" value="Сохранить"
                           class="btn btn-primary" disabled>
                    <input id="contact-cancel-button" type="reset" value="Отмена"
                           class="btn btn-default">
                  </div>
                </div>

              </fieldset>
            </div>
            <div class="col-md-6">
              <fieldset class="field-task">
                <legend>Запланировать действие</legend>
                <div class="form-group task-period-type-group">
                  <label for="task-period-type" class="col-md-4 control-label">Когда </label>
                  <div class="col-md-8">
                    <select id="task-period-type" class="form-control" name="task-period-type">
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
                      <input id="task-date" type="text" class="form-control" name="task-date" />
    						<span class="input-group-addon">
      						<span class="glyphicon glyphicon-calendar"></span>
    					</span>

                    </div>
                    <!--
                    	<input id="task-period-date-view" class="form-control period-date date-picker pick-placehorder"
                    name="task-period-date-view" type="text" value="" size="30" placeholder="кликнуть" autocomplete="off" readonly="">
                    <input id="task-period-date" class="form-control period-date" name="task-period-date" type="hidden" value="">
                    -->


                  </div>
                </div>
                <div class="form-group task-time-group">
                  <label for="task-period-time" class="col-md-4 control-label">Время</label>
                  <div class="col-md-8">
                    <select id="task-period-time" class="form-control" name="task-period-time">
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
                    </select>                  </div>
                </div>
                <div class="form-group task-owner-group">
                  <label for="task-owner" class="col-md-4 control-label">Ответственный</label>
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
                  <label for="task-description" class="col-md-4 control-label">Описание задачи</label>
                  <div class="col-md-8">
						<textarea id="task-description" class="form-control" name="task-description"
                                  rows="3" cols="30">
						</textarea>
                  </div>
                </div>
                <div class="form-group">
                  <div class="text-center">
                    <input id="task-id-button" name="task-submit" type="submit" value="Сохранить"
                           class="btn btn-primary" disabled>
                    <input id="task-cancel-button" type="reset" value="Отмена"
                           class="btn btn-default">
                  </div>
                </div>
              </fieldset>
              <fieldset>
                <legend>Сделки</legend>
                <div class="form-group deal-sum-group" >
                  <label class="col-md-9 control-label">Общее кол-во: ${mapDeals.get("amount")} на ${mapDeals.get("sum")} ${mapDeals.get("currency")}</label>
                </div>
                <div class="form-group deal-sidebar-group">
                  <div class="col-md-12 sidebar-offcanvas" id="sidebar" role="navigation"   style="float:left">
                    <div class="col-md-12 well sidebar-nav">

                      <ul class="nav">
                        <c:forEach items="${deals}" var="deal">
                          <li><a href="#" value="${deal.getId()}">${deal.getName()}|${deal.getStatus().getName()}|${deal.getBudget()}|${deal.getCurrency().getName()}</a></li>
                        </c:forEach>

                      </ul>
                    </div>
                  </div>
                </div>
                <div class="form-group deal-stage-group">
                  <label for="deal-status" class="col-md-4 control-label">Тип</label>
                  <div class="col-md-8">
                    <select id="deal-status" class="form-control" name="deal-status">
                      <c:forEach items="${dealStatuses}" var="dealStatus">
                        <option value="${dealStatus.getId()}">${dealStatus.getName()}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group deal-description-group">
                  <label for="deal-description" class="col-md-4 control-label">Название сделки</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="deal-description" name="deal-description"
                           value size="30" autofocus  >
                  </div>
                </div>
                <div class="form-group deal-budget-group">
                  <label for="deal-budget" class="col-md-4 control-label">Бюджет</label>
                  <div class="col-md-8">
                    <input type="text" class="form-control" id="deal-budget" name="deal-budget"
                           value size="30" autofocus  >
                  </div>
                </div>
                <div class="form-group">
                  <div class="text-center">
                    <input id="deal-id-button" name="deal-submit" type="submit" value="Сохранить"
                           class="btn btn-primary" disabled>
                    <input id="deal-cancel-button" type="reset" value="Отмена"
                           class="btn btn-default">
                  </div>
                </div>
              </fieldset>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>>
<script type="text/javascript">
  $("#task-period-type").change(function(){
    changePeriodType();
  });
</script>
</body>
</html>
