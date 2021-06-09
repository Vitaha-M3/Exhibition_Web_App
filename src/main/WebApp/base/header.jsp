<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 06.06.2021
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<div>
<a id="headLable1" style="border-style: solid" href="/Exhibition?command=main"><fmt:message key="mainPage"/></a>
<c:if test="${userLoggedIn}">
        <a style="border-style: solid" href="/Exhibition?command=logout"><fmt:message key="logOut"/></a>
</c:if>
    <div>
        <form align="right" action="/Exhibition" method="post">
            <fmt:message key="ChouseLang"/>
            <button type="submit" name="language" value="en">EN</button>
            <button type="submit" name="language" value="ru">RU</button>
            <input type="hidden" name="command" value="changeLanguage"/>
        </form>
    </div>
    <h1 style="text-align: center"><fmt:message key="exhibition"/></h1>
<hr>
</div>

