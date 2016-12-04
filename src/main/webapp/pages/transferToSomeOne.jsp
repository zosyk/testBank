<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11/13/16
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Transfer Money</title>

        <link href="<c:url value="../css/style.css" />" rel="stylesheet">
        <link href="<c:url value="../css/transfer_to_someone.css" />" rel="stylesheet">
        <script type="text/javascript" src="../js/jquery/jquery-3.1.1.min.js"></script>
        <script src="../js/custom/transferToSomeone.js"></script>
    </head>
    <body id="body">

        <h1 id="start_text"><spring:message code="transfer.money.to.someone" /></h1>
        <hr width="80%"/>

        <div class="form">
            <div id="form_left">

                <c:set var="createTransaction" value="/createTransactionToSomeOne"/>
                <form action="${createTransaction}" method="post" id="create_transaction_form">

                    <label for="fromCardNumberSelector">From card:</label>
                    <select id="fromCardNumberSelector">
                    </select><br>

                    <p id="error-card-number"></p>
                    <label for="toCardNumber">Whom:</label>

                    <input type="number"  name="toCardNumber" id="toCardNumber" required  placeholder="number card" max="4"/><br>

                    <input type="number"  name="sum" id="sum"  required  placeholder="sum" /> $<br>

                    <input type="hidden"   name="fromCardNumber" id="fromCardNumber"/>
                    <input type="button" id="btn_submit" value="Submit" onclick="createTransactionToSomeOne(document.getElementById('create_transaction_form'))">
                </form>

            </div>
        </div>

        <script>
            var model = '${cards}';
            createSelectFromCards(document.getElementById('fromCardNumberSelector'), model);

        </script>
    </body>
</html>
