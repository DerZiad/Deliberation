<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<title>Login 10</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="/login/css/style.css">

</head>
<body class="img js-fullheight"
	style="background-image: url(/images/background.jpg);">
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section">Deliberation</h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
						<form action="/forgotpwd" class="signin-form" method="POST">
							<div class="form-group">
								<input type="text" name="email" class="form-control"
									placeholder="Email" required>
							</div>
							<div class="form-group">
								<button type="submit"
									class="form-control btn btn-primary submit px-3">Send email</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="/login/js/jquery.min.js"></script>
	<script src="/login/js/popper.js"></script>
	<script src="/login/js/bootstrap.min.js"></script>
	<script src="/login/js/main.js"></script>

</body>
</html>

