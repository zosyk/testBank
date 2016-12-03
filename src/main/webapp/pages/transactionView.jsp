<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11/30/16
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check transaction</title>
    <link href="<c:url value="../css/style.css" />" rel="stylesheet">
    <link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">
</head>
<body>

    <div class="main-container">

        <h2>Check your data</h2>
        <hr />
        <p class="steps">Step 1. Requisites</p>
        <div class="cards-descr-container">

            <div class="cards-labels">
                <p>From card/wallet:</p>
                <p>To card/wallet:</p>
                <p>Sum:</p>
            </div>

            <div class="cards-values">
                <p>${fromCard.number}</p>
                <p>${toCard.number}</p>
                <p>${sum} $</p>
            </div>
        </div>



        <div class="confirm-container">
            <hr />
            <p class="steps">Step 2. Confirm payment</p>

            <div class="confirm-from-container">
                <p>${fromCard.ownerName}</p>
                <p>${sum}  $</p>
            </div>
            <div class="arrow-icon"></div>

            <div class="confirm-to-container">
                <p>${toCard.ownerName}</p>
                <p>${sum}  $</p>
            </div>
        </div>



        <c:set var="confirmTransaction" value="${isBetween ? '/confirmTransactionBetween' : '/confirmTransactionToSomeone'}"/>

        <div class="transfer-btn">
            <form action="${confirmTransaction}" method="post">

                <input type="hidden"  name="sum" value="${sum}"/>
                <input type="hidden"   name="fromID" value="${fromCard.id}"/>
                <input type="hidden"   name="toID" value="${toCard.id}"/>

                <button type="submit" class="btn btn-lg btn-success">Submit</button>

            </form>
        </div>




    </div>

</body>
</html>
