<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 03.06.2021
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<%--        <c:if test="${violations != null}">--%>
<%--            <c:forEach items="${violations}" var="violation">--%>
<%--                <p>${violation}.</p>--%>
<%--            </c:forEach>--%>
<%--        </c:if>--%>

        <form action="/Exhibition" method="post">
            <label for="login">Логин : </label>
            <input type="text" name="login" id="login" value="${login}">
            <label for="name">Имя:</label>
            <input type="text" name="name" id="name" value="${name}">
            <label for="email">Email: </label>
            <input type="text" name="email" id="email" value="${email}">
            <label for="password">Password: </label>
            <input type="text" name="password" id="password" value="${password}">
            <input style="background: aqua" type="submit" name="command" value="registration">
        </form>

</body>
</html>
