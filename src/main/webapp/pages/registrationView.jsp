<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Registration</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">
    <script type="text/javascript" src="../js/custom/registration.js"></script>

    <link href="<c:url value="../css/registration.css" />" rel="stylesheet">

    <link href="../css/custom_input.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../js/customInput.js" type="text/javascript"></script>
    <![endif]-->
</head>

<body class="main">

    <div class="container" >
        <form action="/registration/registerUser" method="post" id="registerUser">
            <h2 class="form-signin-heading">Registration</h2>

            <div class="field">

                <label for="name" class="field-label">Your name</label>

                <input type="text" class="field-input" name="name" id="name" autofocus value="">

            </div>

            <div class="field">

                <label for="surname" class="field-label">Your surname</label>

                <input type="text" class="field-input" name="surname" id="surname"  autofocus value="">


            </div>



            <p id="error-email"></p>

            <div class="field">

                <label for="email" class="field-label">Your email</label>

                <input type="text" class="field-input" name="email" id="email"   value="">

            </div>

            <div class="field">

                <label for="password" class="field-label">Your password</label>

                <input type="password" class="field-input" name="password" id="password"   value="">


            </div>

            <div class="field">

                <label for="password_confirm" class="field-label">Confirm your password</label>

                <input type="password" class="field-input" name="password_confirm" id="password_confirm"  value="">


            </div>

            <button class="btn btn-lg btn-primary btn-block form-item-margin-top" onclick="registration($('#name'), $('#surname'), $('#email'), $('#password'), $('#password_confirm'))" type="button">Register</button>
        </form>

    </div>


</body>
</html>
