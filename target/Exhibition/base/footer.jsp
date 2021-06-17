<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 06.06.2021
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<div>
<footer>
    <hr>
    <p style="text-align: center; border-style: groove; border-color: cadetblue; background: cadetblue">
        @Copyright
        <fmt:message key="footer"/>
    </p>
</footer>
</div>

