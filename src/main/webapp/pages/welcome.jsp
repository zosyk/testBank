<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>JSP List Users Records</title>
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/validate.js"></script>
    <link rel="stylesheet" href="../css/style.css">
    <%--<script type="text/javascript">--%>


        <%--function validate(name) {--%>
            <%--var result  = true;--%>
            <%--if (name.value == '') {--%>
                <%--alert("Fill field!!!")--%>
                <%--result = false;--%>
            <%--} else {--%>
            <%--}--%>
            <%--return result;--%>
        <%--}--%>


    <%--</script>--%>
</head>
<body id="body">

    <div id="transfer_money">
        <form action="/transferMoney" method="get">
            <button class="btn btn-lg btn-primary btn-block" type="submit" id="btn_transf">Transfer Money</button>
        </form>
    </div>
    <div id="users" class="center">

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
                                                                             document.getElementById('users'))">
        </div>
    </div>

    <script type="text/javascript">
        loadUsers(document.getElementById("users"));
    </script>

</body>
</html>











<%--  <form:form action="createCard" method="post" commandName="bankClient" name="form1" onsubmit="return validate(document.getElementById('name'))">
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Spring MVC Form Demo - Registration</h2></td>
            </tr>
            <tr>
                <td>BankClient Name:</td>
                <td><form:input path="name" id="name" /></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address" /></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><form:input path="age" /></td>
            </tr>
            &lt;%&ndash;<tr>&ndash;%&gt;
                &lt;%&ndash;<td>Birthday (mm/dd/yyyy):</td>&ndash;%&gt;
                &lt;%&ndash;<td><form:input path="birthDate" /></td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            &lt;%&ndash;<tr>&ndash;%&gt;
                &lt;%&ndash;<td>Profession:</td>&ndash;%&gt;
                &lt;%&ndash;<td><form:select path="profession" items="${professionList}" /></td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>--%>