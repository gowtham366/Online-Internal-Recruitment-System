<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Admin Page</title>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
#nav {
	width: 100%;
	margin: 0;
	padding: 0;
	text-align: center;
	list-style: none;
	background-color: #f2f2f2;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #ccc;
}

.navbar-default {
	background-color: #66c2ff;
	border-color: #000000;
}

.navbar-fixed-top {
	min-height: 70px;
}

#nav a {
	display: block;
	text-align: center;
	width: 150px; /* fixed width */
	text-decoration: none;
}

.card {
	background-color: #D3D3D3;
	/* just in case there no content*/
	padding: 20px 25px 30px;
	margin: 0 auto 25px;
	margin-top: 50px;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.card-container.card {
	max-width: 500px;
	padding: 40px 40px;
}

.glyphicon {
	font-size: 25px;
}

.navbar-nav {
	font-size: 25px;
}

.logo-wrapper {
	position: absolute;
}

.logo {
	position: relative;
	/* padding-top: 50%; */
	height: 100%;
}
</style>
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
		<ul class="nav navbar-nav navbar-right">
			<li><a href="./OIRSController?action=logout"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
	</nav>
	<div class="text-left">
		<%
			String userId = (String) session.getAttribute("userId");
			String date = (String) session.getAttribute("logindate");
			System.out.println(date);
			out.println("<h4>&nbsp;&nbsp;&nbsp;&nbsp;	Welcome  " + userId + "</h4><br/>");
			out.println("<h4>&nbsp;&nbsp;&nbsp;&nbsp;	Last Login : " + date + "</h4>");
		%>
	</div>
	<form action="OIRSController" method="post">

		<div class="container">
			<div class="card card-container">
				<input type="submit" class="btn btn-info btn-lg" name="action"
					value="Add New User"> <input type="submit"
					class="btn btn-info btn-lg" name="action" value="Assign Role">
				<input type="submit" class="btn btn-info btn-lg" name="action"
					value="Delete User"><br />
				<br /> <a href="./OIRSController?action=generateAdminReport"><input
					type="button" class="btn btn-info btn-lg"
					value="Generate User Report"></a>

			</div>
			<!-- /card-container -->
		</div>

	</form>

</body>
</html>













