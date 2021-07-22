<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@taglib prefix="test" uri="http://mycompany.com" %>--%>
<%@taglib prefix="pagination" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 07.06.2021
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8"?>
<html>
<head>
    <title><fmt:message key="exhibition"/></title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <jsp:include page="/base/bodyStyle.jsp"/>
</head>
<body>
<%--HEADER --%>
<jsp:include page="/base/header.jsp"/>

<%--BODY --%>
<div id="all" style="display: flex">

<div id="userDibLeft">
    <h3><fmt:message key="themeExpo"/></h3>
    <c:choose>
        <c:when test="${choseTheme}">
            <form action="/Exhibition" method="post">
                <select name="themeExpoChange" size="1">
                    <c:forEach items="${themeExpo}" var="themeExpoChange">
                        <option>${themeExpoChange}</option>
                    </c:forEach>
                </select>
                <button type="submit" name="command" value="user_service"><fmt:message key="buttonChoose"/></button>
            </form>

        </c:when>
        <c:otherwise>
            <form action="/Exhibition" method="post">
                <input type="hidden" name="buttonChoseTheme" value="choseTheme">
                <button type="submit" name="command" value="user_service"><fmt:message key="buttonChooseTheme"/></button>
            </form>
        </c:otherwise>
    </c:choose>


</div>

<div id="userDivCenter">
    <c:if test="${errorMessageChoseAnotherDate}">
        <label style="color: red" ><fmt:message key="errorSelectDate"/></label>
    </c:if>
<table id="tableExpo" cellpadding="5" cellspacing="5">
<c:if test="${!showMyTickets}">
<tr>
    <th style="visibility: hidden">    </th>
    <th>
        <form action="/Exhibition" method="post">
        <label><fmt:message key="filterByDate"/></label>
        <input type="date" name="filterDate" value="${filterDate}">
        <button type="submit">OK</button>
        <input type="hidden" name="command" value="user_service">
        </form>
    </th>
    <th style="visibility: hidden">    </th>
    <th style="visibility: hidden">    </th>
    <th>
        <form action="/Exhibition" method="post">
        <label><fmt:message key="buttonSortPrice"/></label>
        <input type="hidden" name="sortPrice" value="true">
        <button type="submit" name="command" value="user_service">$</button>
        </form>
    </th>
</tr>
<tr bgcolor="#f0ffff">
    <th><fmt:message key="themeExpo"/></th>
    <th><fmt:message key="ticketStartDateExpo"/></th>
    <th><fmt:message key="exposedTo"/></th>
    <th><fmt:message key="halls"/></th>
    <th><fmt:message key="ticketPrice"/></th>
    <th><fmt:message key="buttonBuy"/></th>
</tr>
    <c:choose>
        <c:when test="${!filterTheme}">
    <c:forEach var="item" items="${catalog_exposition}">
        <tr bgcolor= "#e6e6fa">

        <td>${item.name}</td>
        <td><fmt:formatDate type = "date" value="${item.date}"/> <fmt:message key="time"/> <fmt:formatDate type="time" pattern="hh:mm" value="${item.date}"/> </td>
        <td><fmt:formatDate type = "date" value="${item.period}"/></td>
        <td>${item.rooms}</td>
        <td>${item.price}UAH</td>
        <td style="align-content: center">
            <c:if test="${userLoggedIn}">
                <form action="/Exhibition" method="get">
                <button type="submit" name="command" value="buyService"><fmt:message key="buttonBuy"/></button>
                    <input type="hidden" name="itemToBuy" value="${item.id}">
                </form>
            </c:if>
            <c:if test="${!userLoggedIn}">
                <button disabled="disabled"><fmt:message key="buttonBuy"/></button>
            </c:if>
        </td>
    </tr>
    </c:forEach>
        </c:when>
        <c:otherwise>
        <c:forEach var="item" items="${catalog_exposition}">
    <tr bgcolor= "#e6e6fa">
        <td>${item.name}</td>
        <td><fmt:formatDate type = "date" value="${item.date}"/> <fmt:message key="time"/> <fmt:formatDate pattern="hh:mm" value="${item.date}"/> </td>
        <td><fmt:formatDate type = "date" value="${item.period}"/></td>
        <td>${item.rooms}</td>
        <td>${item.price}UAH</td>
        <td style="align-content: center">
            <c:if test="${userLoggedIn}">
                <form action="/Exhibition" method="get">
                    <button type="submit" name="command" value="buyService"><fmt:message key="buttonBuy"/></button>
                    <input type="hidden" name="itemToBuy" value="${item.id}">
                </form>
            </c:if>
            <c:if test="${!userLoggedIn}">
                <button disabled="disabled"><fmt:message key="buttonBuy"/></button>
            </c:if>
        </td>

    </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <pagination:pagination/>
</c:if>

    <!--For tickets -->
    <c:if test="${showMyTickets}">
        <h3 align="center"><fmt:message key="myTickets"/></h3>
        <tr bgcolor="#f0ffff">
            <th><b> № </b></th>
            <th><fmt:message key="themeExpo"/></th>
            <th><fmt:message key="ticketStartDateExpo"/></th>
            <th><fmt:message key="exposedTo"/></th>
            <th><fmt:message key="halls"/></th>
            <th><fmt:message key="ticketPrice"/></th>
        </tr>
        <c:forEach var="item" items="${catalogMyTickets}">
            <tr bgcolor= "#e6e6fa">
                <td>${item.numberTicket}</td>
                <td>${item.nameExposition}</td>
                <td><fmt:formatDate type = "date" value="${item.startExposition}"/></td>
                <td><fmt:formatDate type = "date" value="${item.periodExposition}"/></td>
                <td>${item.roomsOnExhibition}</td>
                <td>${item.priceTicket}UAH</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
    <%-- TEST BLOCK  --%>
<%--    <test:nav totalPageCount='5' viewPageCount="3" action="/Exposition"/>--%>
    <%-- TEST BLOCK  --%>
</div>

<div id="userDivRight">
    <h3><fmt:message key="myTickets"/></h3>
    <c:if test="${userLoggedIn}">
    <form action="/Exhibition" method="post">
    <button type="submit" name="command" value="user_service"><fmt:message key="buttonShow"/></button>
        <input type="hidden" name="getMyTickets" value="true">
    </form>
    </c:if>
</div>

</div>
<jsp:include page="/base/footer.jsp"/>
</body>
</html>
