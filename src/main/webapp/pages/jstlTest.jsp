<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11/19/16
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

        <c:set var="dummyName" value="testName123"/>
        <%--<c:out value="${dummyName}">No name</c:out>--%>

        <%--<c:if test="${dummyName == null}">--%>
            <%--Empty--%>
        <%--</c:if>--%>

        <%--<c:choose>--%>
            <%--<c:when test="${dummyName == null}">--%>
                <%--DummyName == null!!!--%>
            <%--</c:when>--%>
            <%--<c:when test="${dummyName == 'testName123'}">--%>
                <%--dummyName = <c:out value="${dummyName}">No name</c:out>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
                <%--Something else--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>

        <c:forEach var="headerValue" items="${headerValues}">
                key: ${headerValue.key} , ${headerValue.value} <br>
        </c:forEach>
    </body>
</html>
