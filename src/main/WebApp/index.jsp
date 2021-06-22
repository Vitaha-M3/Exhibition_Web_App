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
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <jsp:include page="base/bodyStyle.jsp"/>
</head>
<body>
 <jsp:include page="base/header.jsp"/>
 <br><br/>
<c:choose>
    <c:when test="${userLoggedIn}">
        <p aline="center"><a href="/Exhibition?command=main"><fmt:message key="homePage"/></a></p>
    </c:when>
    <c:otherwise>
        <h3 align="center"><fmt:message key="signIn"/></h3>
 <div align="center" style="border-style: solid; border-radius: 10px; padding-bottom: 20px; padding-top: 20px; margin-bottom: 20px">
     <c:if test="${errorLogin}">
         <p style="color: red">${errorMessage}</p>
     </c:if>
     <form action="/Exhibition" method="post">
         <label><input name="login" type="text" placeholder="<fmt:message key="login"/>" required/></label>
         <br><br/>
         <label><input name="password" type="password" placeholder="<fmt:message key="password"/>" required/></label>
         <br><br/>
         <button type="submit"><fmt:message key="login"/> </button>
         <input type="hidden" name="command" value="login"/>
     </form>

 </div>
      <div style="display: flex; align-items: center; justify-content: center">
          <form action="/Exhibition" method="post">
            <button type="submit" name="command" value="go_to_registration" ><fmt:message key="registration"/>-></button>
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
