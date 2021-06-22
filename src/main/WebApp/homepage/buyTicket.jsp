<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 12.06.2021
  Time: 5:21
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8"?>
<html>
<head>
    <title>Buy Tickets</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <jsp:include page="/base/bodyStyle.jsp"/>
</head>
<body>
<jsp:include page="/base/header.jsp"/>
<div align="center">
<h2 align="center"><fmt:message key="yourTicket"/></h2>
<div style="flex: auto; width: 40%; align-content: center; border-style: solid; border-radius: 20px">
    <br/>
    <label><fmt:message key="yourName"/>: </label><b>${user.name}</b>
    <br><br/>
    <label><fmt:message key="yourEmail"/>: <b>${user.email}</b></label>
    <br><br/>
    <label><fmt:message key="purchasedDate"/>: <b><fmt:formatDate type="both" value="<%= new Date()%>"/></b></label>
    <br><br/>
    <label><fmt:message key="themeExpo"/>: <b>${ticketOnExpo.name}</b></label>
    <br><br/>
    <label><fmt:message key="ticketStartDateExpo"/>: <b><fmt:formatDate type="date" value="${ticketOnExpo.date}"/></b></label>
    <br><br/>
    <label><fmt:message key="ticketsEndDate"/>: <b><fmt:formatDate type="date" value="${ticketOnExpo.period}"/></b></label>
    <br><br/>
    <label><fmt:message key="hall"/>: <b>${ticketOnExpo.rooms}</b></label>
    </table>
    <br><br/>
    <h3> <label><fmt:message key="tickPrice"/>: ${ticketOnExpo.price}UAH</label></h3>
    <c:if test="${userLoggedIn}">
    <form action="/Exhibition" method="get">
    <button style="flex: auto; width: 30%; background-color: cornflowerblue; margin-bottom: 20px" type="submit" name="command" value="buyService"><fmt:message key="buttonBuy"/></button>
    <input type="hidden" name="finalBuy" value="true">
        <input type="hidden" name="expoID" value="${ticketOnExpo.id}">
    </form>
    </c:if>
</div>
</div>
<jsp:include page="/base/footer.jsp"/>
</body>
</html>
