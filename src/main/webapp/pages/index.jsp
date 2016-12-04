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
    <script type="text/javascript" src="../js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/custom/index.js"></script>
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

    <div class="hello">
        <h3><spring:message code="hello" /> <span class="full-name">${fullname}!</span></h3>
    </div>

    <div id="cards_table" class="container cards-table all-cards-empty-text">

    </div>

    <div id="pagination" class="center">


    </div>

    <div class="form">

        <div id="create-new-card-container">
            <input type="password" name="card_pin" id="card_pin" required placeholder="enter pincode" maxlength="4"/>
            <input type="password" name="card_conf_pin" id="card_conf_pin" required placeholder="confirm pincode" maxlength="4"/>
            <select id="card_type">
                <option>Credit Card</option>
                <option>Withdraw Card</option>
            </select>
        </div>

        <input type="button" class="btn btn-success btn-create-new-card" value="Create New Credit Card" onclick="return createCard(
                                                                             document.getElementById('card_pin'),
                                                                             document.getElementById('card_conf_pin'),
                                                                             document.getElementById('card_type').value,
                                                                             document.getElementById('create-new-card-container'))">

    </div>

    <script type="text/javascript">
        loadCards(document.getElementById("cards_table"));
    </script>

</body>
</html>
