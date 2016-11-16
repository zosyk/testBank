<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
        <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
        <script src="../js/transfer.js"></script>
    </head>
    <body id="body">

        <h1 id="start_text">Transfer money between your accounts</h1>
        <hr width="80%"/>

        <div class="form">
            <div id="form_left">

                <label for="from_card">From card/wallet:</label>
               <select id="from_card">
                </select><br>

                <label for="to_card">To card/wallet:</label>
                <select id="to_card">
                </select><br>

                <label for="sum">Sum:</label>
                <input type="text"  id="sum" required  placeholder="sum"/> $<br>
                <label for="btn_submit"></label>
                <input type="button" id="btn_submit" value="Submit" onclick="createTransaction(document.getElementById('sum'))">
            </div>
        </div>

        <script>
            var model = '${cards}';
            createSelectFromCards(document.getElementById('from_card'), model);
            createSelectToCards(document.getElementById('to_card'), model);

        </script>
    </body>
</html>
