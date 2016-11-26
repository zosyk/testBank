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
    <script type="text/javascript" src="../js/registration.js"></script>
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>

    <link href="<c:url value="../css/registration.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body class="main">

    <div class="container" >
        <form action="/registerUser" method="post">
            <h2 class="form-signin-heading">Registration</h2>
            <input type="text" class="form-control form-item-margin-top" name="name" id="name" placeholder="your name"  autofocus value="">
            <input type="text" class="form-control form-item-margin-top" name="surname" id="surname" placeholder="your surname"   autofocus value="">
            <input type="text" class="form-control form-item-margin-top" name="email" id="email" placeholder="enter your email"   value="">
            <input type="password" class="form-control form-item-margin-top" name="password" id="password" placeholder="enter your password"  value="">
            <input type="password" class="form-control form-item-margin-top" name="password_confirm" id="password_confirm" placeholder="confirm your password"  value="">
            <button class="btn btn-lg btn-primary btn-block form-item-margin-top" onclick="return registration($('#name'), $('#surname'), $('#email'), $('#password'), $('#password_confirm'))" type="submit">Register</button>
        </form>

    </div>


</body>
</html>
