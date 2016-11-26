<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>JSP List Users Records</title>
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/welcome.js"></script>
    <link rel="stylesheet" href="../css/style.css">
    <link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">
</head>
<body id="body">

    <div class="btn_style">
        <form action="/transferMoney" method="get">
            <button class="btn  btn-primary" type="submit" id="btn_transf">Transfer Money</button>
        </form>
    </div>
    <div class="btn_style">
        <a class="btn  btn-danger" href="<c:url value="/logout" />" role="button">Log out</a>

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
            <input type="button" value="Create New Credit Card" onclick="return createCard(document.getElementById('card_number').value,
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
