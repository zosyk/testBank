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

	<title>Log in</title>

	<!-- Bootstrap core CSS -->
	<link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">
	<script type="text/javascript" src="../js/welcome.js"></script>
	<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>

	<!-- Custom styles for this template -->
	<link href="<c:url value="../css/signin.css" />" rel="stylesheet">
	<link href="<c:url value="../css/style.css" />" rel="stylesheet">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
</head>

<body>

<div class="container" style="width: 300px;">
	<c:url value="/j_spring_security_check" var="loginUrl" />
	<form action="${loginUrl}" method="post">
		<c:if test="${param.error !=null}">
			<!-- Display error message -->
			<div class="error">
				Your login attempt was not successful, try again.<br />
				<%--Reason: #{sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}--%>
			</div>
		</c:if>

		<h2 class="form-signin-heading">Please sign in</h2>
		<input type="text" class="form-control" name="j_username" placeholder="login" required autofocus value="">
		<input type="password" class="form-control" name="j_password" placeholder="password" required value="">
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
	</form>
	<form action="/registration" method="get">
		<button class="btn btn-lg btn-primary btn-block" type="submit" id="btn_reg">Registration</button>
	</form>
</div>

</body>
</html>
