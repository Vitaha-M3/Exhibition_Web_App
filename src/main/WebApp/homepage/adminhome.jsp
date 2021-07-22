<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Time" %>
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

<div id="userDibLeft" >
    <h3><fmt:message key="services"/></h3>
    <form action="/Exhibition" method="post">
    <button type="submit" name="command" value="adminService"><fmt:message key="deleteExposition"/></button>
        <input type="hidden" name="deleteExpo" value="true">
    </form>
    <form action="/Exhibition" method="post">
        <button type="submit" name="command" value="adminService"><fmt:message key="addExposition"/></button>
        <input type="hidden" name="addExpo" value="true">
    </form>
</div>

<div id="userDivCenter">
    <c:if test="${successAdded != null}"><p align="center" STYLE="color: darkred">${successAdded}</p> </c:if>
    <c:if test="${userLoggedIn}">
    <c:choose>
        <c:when test="${addExpoVisible}">
            <div align="center">
            <h2><fmt:message key="addNewExpo"/></h2>
            <form action="/Exhibition" method="post" accept-charset="UTF-8">
                <label for="theme"><fmt:message key="themeExpo"/></label>
                <input type="text" name="theme" id="theme" required>
                <br><br/>
                <label for="dateTime"><fmt:message key="dateTimeOpen"/></label>
                <input type="datetime-local" name="dateTime" id="dateTime" value="2021-07-22T12:00"  required>
                <br><br/>
                <label for="period"><fmt:message key="exposedTo"/></label>
                <input type="date" name="period" id="period" required>
                <br><br/>
                <label for="room1"><input type="checkbox"  name="rooms" id="room1" value="Room1"><fmt:message key="room"/> 1</label>

                <label for="room2"><input type="checkbox"  name="rooms" id="room2" value="Room2"><fmt:message key="room"/> 2</label>

                <label for="room3"><input type="checkbox"  name="rooms" id="room3" value="Room3"><fmt:message key="room"/> 3</label>

                <label for="room4"><input type="checkbox"  name="rooms" id="room4" value="Room4"><fmt:message key="room"/> 4</label>

                <label for="room5"><input type="checkbox"  name="rooms" id="room5" value="Room5"><fmt:message key="room"/> 5</label>

                <label for="room6"><input type="checkbox"  name="rooms" id="room6" value="Room6"><fmt:message key="room"/> 6</label>

                <label for="room7"><input type="checkbox"  name="rooms" id="room7" value="Room7"><fmt:message key="room"/> 7</label>

                <label for="room8"><input type="checkbox"  name="rooms" id="room8" value="Room8"><fmt:message key="room"/> 8</label>
                <br><br/>
                <label for="price"><fmt:message key="ticketPrice"/></label>
                <input type="number" max="400" name="price" id="price" required>
                <br><br/>
                <button type="submit" name="command" value="adminService"><fmt:message key="buttonAdd"/></button>
                <input type="hidden" name="addNewExposition" value="true">
            </form>
            </div>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${!showMeStatistic}">
            <div class="scroll-table">
            <table cellpadding="5" cellspacing="5">
                <thead>
                <tr>
                    <th><fmt:message key="themeExpo"/></th>
                    <th><fmt:message key="dateTimeOpen"/></th>
                    <th><fmt:message key="exposedTo"/></th>
                    <th><fmt:message key="halls"/></th>
                    <th><fmt:message key="ticketPrice"/></th>
                </tr>
                </thead>
            </table>
             <div class="scroll-table-body">
                <table>
                <tbody>
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
                                        <button><fmt:message key="buttonDelete"/></button>
                                        <input type="hidden" name="command" value="adminService">
                                        <input type="hidden" name="itemToDel" value="${item.id}">
                                    </form>

                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
                </table>
             </div>
             </div>
            </c:when>
                <c:otherwise>
                    <h3 align="center"><fmt:message key="statisticH3"/></h3>
                    <div style="display: flex; margin-left: auto;  border-bottom: solid; border-color:black" id="buttonsForStatistic">
                        <form style="margin-left: 5%; margin-right: 5%" action="/Exhibition" method="post">
                        <button type="submit" name="command" value="adminService"><fmt:message key="buttonFinReport"/></button>
                            <input type="hidden" name="showFinStatistic" value="true">
                        </form>
                        <form style="margin-left: 5%; margin-right: 5%" action="/Exhibition" method="post">
                            <button type="submit" name="command" value="adminService"><fmt:message key="buttonPurchasedTickets"/></button>
                            <input type="hidden" name="showQuantityTickForExpo" value="true">
                        </form>
                        <form style="margin-left: 5%; margin-right: 5%" action="/Exhibition" method="post">
                            <button type="submit" name="command" value="adminService"><fmt:message key="buttonUserStatistic"/></button>
                            <input type="hidden" name="showForUserStatistic" value="true">
                        </form>
                    </div>
                    <table align="center" cellpadding="3" cellspacing="3">
                        <c:if test="${showFinStatistic}">
                            <div align="center">
                            <h4><fmt:message key="buttonFinReport"/></h4>
                            <p><fmt:message key="numberSuccessPaid"/>: <b>${quantitySuccessPay}</b></p>
                            <p><fmt:message key="numberNoSucPaid"/>: <b>${quantityNoSuccessPay}</b></p>
                            <p><fmt:message key="sumAllSucPaid"/>: <b>${sumAllSuccessPay}UAH</b></p>
                            </div>
                        </c:if>
                        <c:if test="${showQuantityTickForExpo}">
                            <h4 align="center"><fmt:message key="buttonPurchasedTickets"/></h4>
                                <tr>
                                    <th><fmt:message key="IDExpo"/></th>
                                    <th><fmt:message key="themeExpo"/></th>
                                    <th><fmt:message key="exposedTo"/></th>
                                    <th><fmt:message key="purchasedTicket"/></th>
                                    <th><fmt:message key="revenue"/></th>
                                </tr>
                                <c:forEach var="itemList" items="${tablePurchasedTickets}">
                                    <tr>
                                        <td>${itemList.get("expoId")}</td>
                                        <td>${itemList.get("name")}</td>
                                        <td>${itemList.get("dateOfAnd")}</td>
                                        <td>${itemList.get("countBuyTickets")}</td>
                                        <td>${itemList.get("sumBilling")}</td>
                                    </tr>
                                </c:forEach>
                        </c:if>
                        <c:if test="${showForUserStatistic}">
                            <h4 align="center"><fmt:message key="buttonUserStatistic"/></h4>
                            <tr>
                                <th><fmt:message key="IDUser"/></th>
                                <th><fmt:message key="name"/></th>
                                <th>Email</th>
                                <th><fmt:message key="purchasedTicket"/></th>
                                <th><fmt:message key="IDExpoVisited"/></th>
                                <th><fmt:message key="revenue"/></th>
                            </tr>
                            <c:forEach var="itemUser" items="${tableUsersStatistic}">
                                <tr>
                                    <td>${itemUser.get("userId")}</td>
                                    <td>${itemUser.get("name")}</td>
                                    <td>${itemUser.get("email")}</td>
                                    <td>${itemUser.get("purchasedTickets")}</td>
                                    <td>${itemUser.get("visitThisExposition")}</td>
                                    <td>${itemUser.get("sumBuy")}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    </c:if>
</div>

<div id="userDivRight">
    <h3><fmt:message key="viewStatistic"/></h3>
    <form action="/Exhibition" method="post">
    <button type="submit" name="command" value="adminService"><fmt:message key="buttonShow"/></button>
        <input type="hidden" name="showStatistic" value="true">
    </form>
</div>

</div>
<jsp:include page="/base/footer.jsp"/>
</body>
</html>
