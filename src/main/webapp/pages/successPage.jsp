<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 12/2/16
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success transaction</title>
    <link href="<c:url value="../css/style.css" />" rel="stylesheet">
    <link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">
</head>
<body>
        <h1 class="successfull-text">successful operation</h1>

        <div class="return-btn">
            <form action="${pageContext.request.contextPath}/success" method="get">

                <button type="submit" class="btn btn-lg btn-success">Return to the cabinet</button>

            </form>
        </div>
</body>
</html>
