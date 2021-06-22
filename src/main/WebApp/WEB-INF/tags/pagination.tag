<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${noOfPages > 1}">

<%--For displaying PageTable numbers.
The when condition does not display a link for the current page--%>
<table style="border-radius: 10px; border: 1px solid black; border-collapse: collapse"  cellpadding="3" cellspacing="3">

    <tr>
            <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPageTable != 1}">
            <td><a style="text-decoration: none" href="/Exhibition?command=user_service&pageTable=${currentPageTable - 1}"><--</a></td>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPageTable eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a style="text-decoration: none" href="/Exhibition?command=user_service&pageTable=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>

            <%--For displaying Next link --%>
        <c:if test="${currentPageTable lt noOfPages}">
            <td><a style="text-decoration: none" href="/Exhibition?command=user_service&pageTable=${currentPageTable + 1}">--></a></td>
        </c:if>

    </tr>
</table>

</c:if>