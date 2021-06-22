<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 06.06.2021
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="bodyStyle.jsp"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<div style="display: flex; ">
    <div style="width: 12%">
        <form action="/Exhibition" method="post">
        <c:if test="${!userLoggedIn}">
            <label><fmt:message key="user"/> <fmt:message key="guest"/></label>
            <br/>
            <button type="submit" name="command" value="login"><fmt:message key="signIn"/></button>
        </c:if>
        <c:if test="${userLoggedIn}">
            <label><fmt:message key="user"/> ${user.name}</label>
            <br/>
            <button type="submit" name="command" value="logout"><fmt:message key="logOut"/></button>
        </c:if>
        </form>
    </div>

    <div style="width: 76%">
        <h1 style="text-align: center"><fmt:message key="exhibition"/></h1>
    </div>

    <div align="right" style="width: 12%">
        <form  action="/Exhibition" method="post">
            <label><fmt:message key="ChooseLang"/></label>
            <br>
            <button type="submit" name="language" value="en">EN</button>
            <button type="submit" name="language" value="ru">RU</button>
            <input type="hidden" name="command" value="changeLanguage"/>
        </form>
    </div>
</div>
<hr class="hr-shelf">

