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
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script src="../js/transferToSomeone.js"></script>
    </head>
    <body id="body">

        <h1 id="start_text"><spring:message code="transfer.money.to.someone" /></h1>
        <hr width="80%"/>

        <div class="form">
            <div id="form_left">

                <c:set var="createTransaction" value="/createTransactionToSomeOne"/>
                <form action="${createTransaction}" method="post" id="create_transaction_form">
                    <label for="from_card">From card:</label>
                    <select id="from_card">
                    </select><br>

                    <label for="toCardNumber">Whom:</label>

                    <input type="text"  name="toCardNumber" id="toCardNumber" required  placeholder="number card"/><br>

                    <input type="text"  name="sum"  required  placeholder="sum"/> $<br>

                    <input type="hidden"   name="fromID"/>
                    <input type="button" id="btn_submit" value="Submit" onclick="createTransactionToSomeOne(document.getElementById('create_transaction_form'))">
                </form>

            </div>
        </div>

        <script>
            var model = '${cards}';
            createSelectFromCards(document.getElementById('from_card'), model);

        </script>
    </body>
</html>
