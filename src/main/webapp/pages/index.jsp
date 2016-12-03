<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Welcome to West bank!</title>
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/welcome.js"></script>
    <link rel="stylesheet" href="../css/style.css">
    <link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">
</head>
<body>

    <div class="navigation-bar">
        <a class="navbar-item" href="/transferMoneyBetween"><spring:message code="transfer.money.between.your.cards" /></a>
        <a class="navbar-item" href="/transferMoneyToSomeone"><spring:message code="transfer.money.to.someone" /></a>
        <a class="navbar-item" href="<c:url value="/logout" />">Log out</a>
        <a href="?lang=en"><img  src="../img/ic_en.jpg" width="30" height="20"></a>
        <a href="?lang=ru"><img  src="../img/ic_ru.png" width="30" height="20"></a>
    </div>

    <div id="cards_table" class="container">

    </div>

    <div id="pagination" class="center">


    </div>

    <div class="form">
        <h1>Create card</h1>
        <div>
            <input type="text"  name="card_number" id="card_number" required autofocus placeholder="card number"/>
            <input type="password" name="card_ps" id="card_ps" required placeholder="card password"/>
            <input type="password" name="card_conf_ps" id="card_conf_ps" required placeholder="confirm card password"/>
            <select id="card_type">
                <option>Credit Card</option>
                <option>Withdraw Card</option>
            </select>
            <input type="button" class="btn btn-success" value="Create New Credit Card" onclick="return createCard(document.getElementById('card_number').value,
                                                                             document.getElementById('card_ps').value,
                                                                             document.getElementById('card_type').value,
                                                                             document.getElementById('cards_table'))">
        </div>
    </div>

    <script type="text/javascript">
        loadUsers(document.getElementById("cards_table"));
    </script>

</body>
</html>
