<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11/6/16
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>History</title>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/transfer.js"></script>
</head>
    <body id="body">

        <div id="transactions" class="container">
        </div>

    <script>
        tableTransactions('${transactions}')
    </script>

    </body>
</html>
