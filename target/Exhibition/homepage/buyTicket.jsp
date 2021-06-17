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
<h2 align="center">КУПИТЬ БИЛЕТ</h2>
<div style="flex: auto; width: 40%; align-content: center; border-style: solid; border-radius: 20px">
    <br/>
    <label>Your name: </label><b>${user.name}</b>
    <br><br/>
    <label>Your email: <b>${user.email}</b></label>
    <br><br/>
    <label>Purchase date : <b><fmt:formatDate type="both" value="<%= new Date()%>"/></b></label>
    <br><br/>
    <label>Exposition theme: <b>${ticketOnExpo.name}</b></label>
    <br><br/>
    <label>Start date of the exhibition : <b><fmt:formatDate type="date" value="${ticketOnExpo.date}"/></b></label>
    <br><br/>
    <label>Exposition end date : <b><fmt:formatDate type="date" value="${ticketOnExpo.period}"/></b></label>
    <br><br/>
    <label>Hall : <b>${ticketOnExpo.rooms}</b></label>
    </table>
    <br><br/>
    <h3> <label>Price: ${ticketOnExpo.price}UAH</label></h3>
    <c:if test="${userLoggedIn}">
    <form action="/Exhibition" method="post">
    <button style="flex: auto; width: 30%; background-color: cornflowerblue; margin-bottom: 20px" type="submit" name="command" value="buy_service">BUY</button>
    <input type="hidden" name="finalBuy" value="true">
        <input type="hidden" name="expoID" value="${ticketOnExpo.id}">
    </form>
    </c:if>
</div>
</div>
<jsp:include page="/base/footer.jsp"/>
</body>
</html>
