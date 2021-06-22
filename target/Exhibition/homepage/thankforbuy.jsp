<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 13.06.2021
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8"?>
<html>
<head>
    <title>Title</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <jsp:include page="/base/bodyStyle.jsp"/>
</head>
<body>
<jsp:include page="/base/header.jsp"/>
<div align="center">
<h1><fmt:message key="thanksBuy"/>!!!</h1>
    <a href="/Exhibition?command=main"><fmt:message key="homePage"/></a>
</div>
<jsp:include page="/base/footer.jsp"/>
</body>
</html>
