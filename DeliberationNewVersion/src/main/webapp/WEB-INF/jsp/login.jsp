<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Authentification - Déliberation Bachelor</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/png" href="images/icons/favicon.ico" />

<link rel="stylesheet" type="text/css"
	href="/login/vendor/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="/login/font-awesome.css">

<link rel="stylesheet" type="text/css"
	href="/login/material-design-iconic-font.css">

<link rel="stylesheet" type="text/css" href="/login/animate.css">

<link rel="stylesheet" type="text/css" href="/login/hamburgers.css">

<link rel="stylesheet" type="text/css" href="/login/animsition.css">

<link rel="stylesheet" type="text/css" href="/login/select2.css">

<link rel="stylesheet" type="text/css" href="/login/daterangepicker.css">

<link rel="stylesheet" type="text/css" href="/login/util.css">
<link rel="stylesheet" type="text/css" href="/login/main.css">

</head>
<body>
	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('/images/bg-01.jpg');">
			<div class="wrap-login100">
				<form method="POST" action="/signin"
					class="login100-form validate-form">
					<span class="login100-form-logo"> <img
						src="/assets/images/logo-inverse.png">
					</span> <span class="login100-form-title p-b-34 p-t-27"> Log in </span>
					<c:if test="${not empty err}">
						<div style="margin-top: 40px" class="alert alert-danger"
							role="alert">${err}</div>
					</c:if>
					<div class="wrap-input100 validate-input"
						data-validate="Enter username">
						<input class="input100" type="text" name="username"
							placeholder="Username"> <span class="focus-input100"
							data-placeholder="&#xf207;"></span>
					</div>
					<div class="wrap-input100 validate-input"
						data-validate="Enter password">
						<input class="input100" type="password" name="password"
							placeholder="Password"> <span class="focus-input100"
							data-placeholder="&#xf191;"></span>
					</div>
					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox"
							name="remember-me"> <label class="label-checkbox100"
							for="ckb1"> Se rappeler de moi </label>
					</div>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>

	<script src="/login/jquery-3.js"></script>

	<script src="/login/animsition.js"></script>

	<script src="/login/popper.js"></script>
	<script src="/login/bootstrap.js"></script>

	<script src="/login/select2.js"></script>

	<script src="/login/moment.js"></script>
	<script src="/login/daterangepicker.js"></script>

	<script src="/login/countdowntime.js"></script>

	<script src="js/main.js"></script>

	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	  gtag('config', 'UA-23581568-13');
	</script>
</body>
</html>