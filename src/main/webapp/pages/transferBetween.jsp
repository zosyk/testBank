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
        <script type="text/javascript" src="../js/jquery/jquery-3.1.1.min.js"></script>
        <script src="../js/custom/transferBetweenMyCards.js"></script>
    </head>
    <body>

        <h1 id="start_text"><spring:message code="transfer.money.between.your.cards" /></h1>
        <hr width="80%"/>

        <div class="form">
            <div id="form_left">

                <c:set var="createTransaction" value="/createTransactionBetween"/>
                <form action="${createTransaction}" method="post" id="create_transaction_form">
                    <label for="from_card">From card:</label>
                    <select id="from_card">
                    </select><br>

                    <label for="to_card">To card:</label>
                    <select id="to_card">
                    </select><br>

                    <input type="text"  name="sum" required  placeholder="sum"/> $<br>

                    <input type="hidden"   name="fromID"/>
                    <input type="hidden"   name="toID"/>
                    <input type="button" id="btn_submit" value="Submit" onclick="createTransactionBetween(document.getElementById('create_transaction_form'))">
                </form>

            </div>
        </div>

        <script>
            var model = '${cards}';
            createSelectFromCards(document.getElementById('from_card'), model);
            createSelectToCards(document.getElementById('to_card'), model);

        </script>
    </body>
</html>
