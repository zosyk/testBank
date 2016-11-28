<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>West bank</title>

	<!-- Bootstrap core CSS -->
	<link href="<c:url value="../css/bootstrap.css" />" rel="stylesheet">
	<script type="text/javascript" src="../js/welcome.js"></script>
	<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>

	<!-- Custom styles for this template -->
	<link href="<c:url value="../css/style.css" />" rel="stylesheet">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
</head>

<body id="body">

	<div class="main">
		<div class="header">
			<h1>West Bank</h1>
		</div>
		<div class="btn_style">
			Language : <a href="?lang=en"><img  src="../img/ic_en.jpg" width="30" height="20"></a>
			<a href="?lang=ru"><img  src="../img/ic_ru.png" width="30" height="20"></a>
		</div>
		<div class="icons">
			<div class="feature">
				<div class="icon    statements"></div>
				<div class="title"><spring:message code="statements.of.card"/></div>
				<div>Control cash flow on your card / account.</div>
			</div>
			<div class="feature">
				<div class="icon mobile"></div>
				<div class="title">Recharge your mobile</div>
				<div>Instantly. Mobile operators in 140 countries of over the world.</div>
			</div>
			<div class="feature">
				<div class="icon utilities"></div>
				<div class="title">Regular utilities</div>
				<div>Automatic payment of your utility bills: water, gas and other services.</div>
			</div>
			<div class="feature">
				<div class="icon translation"></div>
				<div class="title">Translations worldwide</div>
				<div>On the card VISA / MasterCard, WesternUnion, on account Liqpay.</div>
			</div>
			<div class="feature">
				<div class="icon deposits"></div>
				<div class="title">Deposits</div>
				<div>Remotely open with favorable rates and manage savings.</div>
			</div>
		</div>



		<div class="action-container">
			<div class="action-form">

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
					<input  type="text" class="form-control form-item-margin-top" name="j_username" placeholder="login" required autofocus value="">
					<input type="password" class="form-control form-item-margin-top" name="j_password" placeholder="password" required value="">
					<button class="btn  btn-lg btn-primary btn-block form-item-margin-top" type="submit">Sign In</button>
				</form>
				<form action="${pageContext.request.contextPath}/registration" method="get">
					<button class="btn btn-lg btn-primary btn-block form-item-margin-top" type="submit" id="btn_reg">Registration</button>
				</form>

			</div>

		</div>
	</div>

</body>
</html>
