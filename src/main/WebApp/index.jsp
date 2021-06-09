<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 27.05.2021
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="exhibition"/></title>
    <jsp:include page="base/bodyStyle.jsp"/>
</head>
<body>
 <jsp:include page="base/header.jsp"/>
 <br/>
    <h3 align="center"><fmt:message key="login"/></h3>

<c:choose>
    <c:when test="${userLoggedIn}">
        <a style="align-content: center" href="/Exhibition?command=homepage"><fmt:message key="homePage"/></a>
    </c:when>
    <c:otherwise>
 <div align="center">
     <form action="/Exhibition" method="post">
         <label><input name="login" type="text" placeholder="<fmt:message key="login"/>"/></label>
         <br><br/>
         <label><input name="password" type="password" placeholder="<fmt:message key="password"/>"/></label>
         <br><br/>
         <button type="submit"><fmt:message key="login"/> </button>
         <input type="hidden" name="command" value="login"/>
     </form>
     <p style="color: red">${errorMessage}</p>
 </div>
        <div style="display: flex; align-items: center; justify-content: center">
            <form action="/registration.jsp" method="post">
            <button type="submit" > <fmt:message key="registration"/>-></button>
            </form>
        <form action="/Exhibition" method="post">

            <button type="submit" name="command" value="main"><fmt:message key="mainPage"/>-></button>
        </form>
        </div>
    </c:otherwise>
</c:choose>

    <jsp:include page="base/footer.jsp"/>
</body>
</html>
