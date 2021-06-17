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
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<div>
    <div align="right">
        <form  action="/Exhibition" method="post">
            <label><fmt:message key="ChooseLang"/></label>
            <button type="submit" name="language" value="en">EN</button>
            <button type="submit" name="language" value="ru">RU</button>
            <input type="hidden" name="command" value="changeLanguage"/>
        </form>
    </div>
    <c:choose>
        <c:when test="${userLoggedIn}">
            <p>USER ${user.name}</p>
            <a style="border-style: solid" href="/Exhibition?command=logout"><fmt:message key="logOut"/></a>
        </c:when>
        <c:otherwise>
            <form action="/Exhibition" method="post">
                <button type="submit" name="command" value="login"><fmt:message key="signIn"/></button>
            </form>
        </c:otherwise>
    </c:choose>


    <h1 style="text-align: center"><fmt:message key="exhibition"/></h1>
<hr>
</div>

