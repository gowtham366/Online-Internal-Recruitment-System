<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
font {
	vertical-align: center;
}

.card {
	background-color: #F7F7F7;
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

.wrapper {
	text-align: center;
}

.card-container.card {
	max-width: 350px;
	padding: 40px 40px;
}

.glyphicon {
	font-size: 25px;
}

.navbar-nav {
	font-size: 25px;
}
</style>
<title>RM Dashboard</title>
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
		<h4>Welcome Resource Manager...</h4>
		<%
			String date = (String) session.getAttribute("logindate");
			System.out.println(date);
			out.println("Last Login : " + date);
		%>
	</div>

	<br /></br>
	<h3>Resource Manager Dashboard</h3>
	<div class="container">
		<div class="card card-container">
			<a href="./OIRSController?action=RaiseRequisitions"><input
				type="button" value="Raise Requisition" class="btn btn-info btn-lg"></a>
			<br> <br> <a href="./OIRSController?action=Accept/Reject"><input
				type="button" value="Accept/Reject Resource"
				class="btn btn-info btn-lg"></a> <br> <br> <a
				href="./OIRSController?action=viewRmProject"><input
				type="button" value="View Project" class="btn btn-info btn-lg"></a>
			<br> <br> <a href="./OIRSController?action=closeRmProject"><input
				type="button" value="Close Project" class="btn btn-info btn-lg"></a>
			<br> <br> <a
				href="./OIRSController?action=unAssignEmployee"><input
				type="button" value="Unassign Employee" class="btn btn-info btn-lg"></a>
			<br> <br> <a
				href="./OIRSController?action=generateRMReport"><input
				type="button" value="Generate Report" class="btn btn-info btn-lg"></a>
		</div>
		<!-- /card-container -->
	</div>
	<a href="#" onclick="history.go(-1)">Go Back </a>
</body>
</html>