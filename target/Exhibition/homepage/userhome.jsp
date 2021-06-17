<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<c:if test="${!userLoggedIn}">
    <p>USER Guest</p>
</c:if>
<jsp:include page="/base/header.jsp"/>

<%--BODY --%>
<div id="all" style="display: flex">

<div id="userDibLeft">
    <h3>Тема експозиции</h3>
    <c:choose>
        <c:when test="${choseTheme}">
            <form action="/Exhibition" method="post">
                <select name="themeExpoChange" size="1">
                    <c:forEach items="${themeExpo}" var="themeExpoChange">
                        <option>${themeExpoChange}</option>
                    </c:forEach>
                </select>
                <button type="submit" name="command" value="user_service">Выбрать</button>

            </form>

        </c:when>
        <c:otherwise>
            <form action="/Exhibition" method="post">
                <input type="hidden" name="buttonChoseTheme" value="choseTheme">
                <button type="submit" name="command" value="user_service">Выбрать тему</button>
            </form>
        </c:otherwise>
    </c:choose>


</div>

<div id="userDivCenter">

<table id="tableExpo">
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
        <label>Sort By Price</label>
        <input type="hidden" name="sortPrice" value="${sortPrice}">
        <button type="submit" name="command" value="user_service">Sort</button>
        </form>
    </th>
</tr>
<tr>
    <th>Тема экспозиции</th>
    <th>Дата и время открытия экспозиции</th>
    <th>Выставлена до</th>
    <th>Залы на выставке</th>
    <th>Цена билета</th>
    <th>КУПИТЬ</th>
</tr>
    <c:choose>
        <c:when test="${!filterTheme}">
    <c:forEach var="item" items="${catalog_exposition}">
        <tr>

        <td>${item.name}</td>
        <td><fmt:formatDate type = "date" value="${item.date}"/> <fmt:message key="time"/> <fmt:formatDate pattern="hh:mm" value="${item.date}"/> </td>
        <td><fmt:formatDate type = "date" value="${item.period}"/></td>
        <td>${item.rooms}</td>
        <td>${item.price}UAH</td>
        <td style="align-content: center">
            <c:if test="${userLoggedIn}">
                <form action="/Exhibition" method="post">
                <button type="submit" name="command" value="buy_service">Купить</button>
                    <input type="hidden" name="itemToBuy" value="${item.id}">
                </form>
            </c:if>
            <c:if test="${!userLoggedIn}">
                <button disabled="disabled">Купить</button>
            </c:if>
        </td>
    </tr>
    </c:forEach>
        </c:when>
        <c:otherwise>
        <c:forEach var="item" items="${catalog_exposition}">
    <tr>
        <td>${item.name}</td>
        <td><fmt:formatDate type = "date" value="${item.date}"/> <fmt:message key="time"/> <fmt:formatDate pattern="hh:mm" value="${item.date}"/> </td>
        <td><fmt:formatDate type = "date" value="${item.period}"/></td>
        <td>${item.rooms}</td>
        <td>${item.price}UAH</td>
        <td style="align-content: center">
            <c:if test="${userLoggedIn}">
                <form action="/Exhibition" method="post">
                    <button type="submit" name="command" value="buy_service">Купить</button>
                    <input type="hidden" name="itemToBuy" value="${item.id}">
                </form>
            </c:if>
            <c:if test="${!userLoggedIn}">
                <button disabled="disabled">Купить</button>
            </c:if>
        </td>

    </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
</div>

<div id="userDivRight"><h3>МОИ БИЛЕТЫ</h3></div>

</div>
<jsp:include page="/base/footer.jsp"/>
</body>
</html>
