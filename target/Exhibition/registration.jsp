<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 03.06.2021
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="exhibition"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <jsp:include page="base/bodyStyle.jsp"/>
</head>
<body>
<jsp:include page="base/header.jsp"/>

<div align="center" style="border: solid; border-radius: 10px; margin-bottom: 20px; padding-bottom: 20px">
        <h2><fmt:message key="registerh2"/> </h2>

    <c:if test="${violations != null}">
        <c:forEach items="${violations}" var="violation">
            <p style="color: darkred">${violation}</p>
        </c:forEach>
    </c:if>

        <form action="/Exhibition" method="post" accept-charset="UTF-8">
            <label for="login"><fmt:message key="login"/>: </label>
            <input type="text" name="login" id="login" value="${login}" required>
            <label for="name"><fmt:message key="name"/>:</label>
            <input type="text" name="name" id="name" value="${name}" required>
            <label for="email">Email: </label>
            <input type="email" name="email" id="email" value="${email}" required>
            <label for="password"><fmt:message key="password"/>: </label>
            <input type="password" name="password" id="password" value="${password}" required>
            <button type="submit"><fmt:message key="registration"/></button>
            <input type="hidden" name="command" value="registration">
        </form>
</div>

<jsp:include page="base/footer.jsp"/>
</body>
</html>
