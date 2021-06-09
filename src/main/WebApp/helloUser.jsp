<%--
  Created by IntelliJ IDEA.c
  User: Vitaliy
  Date: 03.06.2021
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>HelloUser</title>
</head>
<body>
    <h1>Спасибо за регистрацию!</h1>
    <h2>Ваши введённые данные:</h2>
    <p><strong>Логин:</strong> ${login}</p>
    <p><strong>Имя:</strong> ${name}</p>
    <p><strong>Email: </strong>${email}</p>
</body>
</html>
