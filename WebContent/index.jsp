<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.card {
	background-color: #D3D3D3;
	/* just in case there no content*/
	padding: 20px 25px 30px;
	margin: 0 auto 25px;
	margin-top: 5px;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.card-container.card {
	max-width: 350px;
	padding: 40px 40px;
}
</style>
<title>Login Page</title>
</head>

<body class="text-center">
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">

		<ul class="nav navbar-nav navbar-center">
			<center>
				<p>
					<font size="5" color="#ffffff">Online Internal Recruitment
						System</font>
				</p>
			</center>
		</ul>
	</div>
	</nav>
	<br />
	<br />
	<h2 class="h3 mb-15">User Login</h2>
	<br />

	<form class="form-inline" action="OIRSController" method="post">
		<table border="1">
			<!-- <div class = "container"> -->



			<div class="container">
				<div class="card card-container">

					<div class="form-group">
						<label for="UserId">Enter User Id :</label> <input type="text"
							class="form-control" id="Id" placeholder="Enter User Id"
							name="userId" pattern="[a-zA-Z0-9]{3,15}" required="">
					</div>
					<br /> <br />
					<div class="form-group">
						<label for="pwd">Password:</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password"
							class="form-control" id="pwd" placeholder="Enter password"
							name="userPassword" required="">
					</div>
					<br /> <br /> <input type="submit" class="btn btn-info"
						name="action" value="Login" />
				</div>
				<!-- /card-container -->
			</div>
		</table>
	</form>
</body>
</html>