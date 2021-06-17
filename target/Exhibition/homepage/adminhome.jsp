<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 07.06.2021
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8"?>
<html>
<head>
    <title>Admin Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <jsp:include page="/base/bodyStyle.jsp"/>
</head>
<body>
<jsp:include page="/base/header.jsp"/>
<div id="all" style="display: flex">

<div id="userDibLeft">
    <h3>Сервисы</h3>
    <form action="/Exhibition" method="post">
    <button type="submit" name="command" value="adminService">Удалить экспозицию</button>
        <input type="hidden" name="deleteExpo" value="true">
    </form>
    <form action="/Exhibition" method="post">
        <button type="submit" name="command" value="adminService">Добавить экспозицию</button>
        <input type="hidden" name="addExpo" value="true">
    </form>
</div>

<div id="userDivCenter">
    <c:if test="${successAdded != null}"><p align="center" STYLE="color: darkred">${successAdded}</p> </c:if>
    <c:if test="${userLoggedIn}">
    <c:choose>
        <c:when test="${addExpoVisible}">
            <div align="center">
            <h2>Добавить новую экспозицию</h2>
            <form action="/Exhibition" method="post" accept-charset="UTF-8">
                <label for="theme">Тема экспозиции</label>
                <input type="text" name="theme" id="theme" required>
                <br><br/>
                <label for="dateTime">Дата и время открытия</label>
                <input type="datetime-local" name="dateTime" id="dateTime" value="2021-06-25T12:00"  required>
                <br><br/>
                <label for="period">Выставлена до</label>
                <input type="date" name="period" id="period" required>
                <br><br/>
                <label for="room1"><input type="checkbox"  name="rooms" id="room1" value="Room1">Зал 1</label>

                <label for="room2"><input type="checkbox"  name="rooms" id="room2" value="Room2">Зал 2</label>

                <label for="room3"><input type="checkbox"  name="rooms" id="room3" value="Room3">Зал 3</label>

                <label for="room4"><input type="checkbox"  name="rooms" id="room4" value="Room4">Зал 4</label>

                <label for="room5"><input type="checkbox"  name="rooms" id="room5" value="Room5">Зал 5</label>

                <label for="room6"><input type="checkbox"  name="rooms" id="room6" value="Room6">Зал 6</label>

                <label for="room7"><input type="checkbox"  name="rooms" id="room7" value="Room7">Зал 7</label>

                <label for="room8"><input type="checkbox"  name="rooms" id="room8" value="Room8">Зал 8</label>
                <br><br/>
                <label for="price">Цена билета</label>
                <input type="number" max="400" name="price" id="price" required>
                <br><br/>
                <button type="submit" name="command" value="adminService">Добавить</button>
                <input type="hidden" name="addNewExposition" value="true">
            </form>
            </div>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${!showMeStatistic}">
            <table>
                <tr>
                    <th>Тема экспозиции</th>
                    <th>Дата открытия экспозиции</th>
                    <th>Выставлена до</th>
                    <th>Залы на выставке</th>
                    <th>Цена билета</th>
                </tr>
                <c:forEach var="item" items="${catalog_exposition}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.date}</td>
                        <td>${item.period}</td>
                        <td>${item.rooms}</td>
                        <td>${item.price}UAH</td>
                        <c:if test="${deleteExpoVisible}">
                            <td>
                                <c:if test="${userLoggedIn}">

                                    <form action="/Exhibition" method="post">
                                        <button>Удалить</button>
                                        <input type="hidden" name="command" value="adminService">
                                        <input type="hidden" name="itemToDel" value="${item.id}">
                                    </form>

                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            </c:when>
                <c:otherwise>
                    <h3 align="center">СТАТИСТИКА ПОСЕЩЕНИЙ И ПРОДАННЫХ БИЛЕТОВ</h3>
                    <div style="display: flex; margin-left: auto;  border-bottom: solid; border-color:black" id="buttonsForStatistic">
                        <form style="margin-left: 5%; margin-right: 5%" action="/Exhibition" method="post">
                        <button type="submit" name="command" value="adminService">Финансовый отчет</button>
                            <input type="hidden" name="showFinStatistic" value="true">
                        </form>
                        <form style="margin-left: 5%; margin-right: 5%" action="/Exhibition" method="post">
                            <button type="submit" name="command" value="adminService">Куплено билетов по экспозициям</button>
                            <input type="hidden" name="showQuantityTickForExpo" value="true">
                        </form>
                        <form style="margin-left: 5%; margin-right: 5%" action="/Exhibition" method="post">
                            <button type="submit" name="command" value="adminService">Статистика по пользователям</button>
                            <input type="hidden" name="showForUserStatistic" value="true">
                        </form>
                    </div>
                    <table>
                        <c:if test="${showFinStatistic}">
                            <div align="center">
                            <h4>Финансовый отчет</h4>
                            <p>Количество успешно оплаченных билетов:  <b>${quantitySuccessPay}</b></p>
                            <p>Количество незавершенных оплат: <b>${quantityNoSuccessPay}</b></p>
                            <p>Сумма всех удачных оплат: <b>${sumAllSuccessPay}UAH</b></p>
                            </div>
                        </c:if>
                        <c:if test="${showQuantityTickForExpo}">
                            <p>Куплено билетов по экспозициям</p>
                        </c:if>
                        <c:if test="${showForUserStatistic}">
                            <p>Статистика по пользователям</p>
                        </c:if>
                    </table>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    </c:if>
</div>

<div id="userDivRight">
    <h3>Посмотреть статистику</h3>
    <form action="/Exhibition" method="post">
    <button type="submit" name="command" value="adminService">Показать</button>
        <input type="hidden" name="showStatistic" value="true">
    </form>
</div>

</div>
<jsp:include page="/base/footer.jsp"/>
</body>
</html>
