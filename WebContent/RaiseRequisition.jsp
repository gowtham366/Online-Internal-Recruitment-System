<%@page import="com.recruitmentSystem.bean.ProjectBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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

<title>Raise Requisition</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
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
	max-width: 750px;
	padding: 40px 40px;
}

.glyphicon {
	font-size: 25px;
}

.navbar-nav {
	font-size: 25px;
}
</style>
</head>
<body style="text-align: center">
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


	<%
		List<ProjectBean> list = (ArrayList<ProjectBean>) request
				.getAttribute("projectDetails");
		if (list.size() > 0) {
	%>
	<form class="form-inline" method="post" action="OIRSController">
		</br>
		<h3>Raise Requisition</h3>
		<div class="container">
			<div class="card card-container">
				</br> </br>
				<div class="form-group">
					<label for="projId">Select Project : </label>
					<!-- <input type="text" class="form-control" id="projId" placeholder="Enter Project Id" name="projId"> -->
					<select name="projId" required="">
						<%
							for (int i = 0; i < list.size(); i++) {
									ProjectBean proBean = list.get(i);
						%>
						<Option value="<%=proBean.getProjectId()%>"><%=proBean.getProjectId() + "  "
							+ proBean.getProjectName()%></Option>
						<%
							}
						%>
					</select>
				</div>
				<br /> <br />

				<div class="form-group">
					<label for="skill">Enter skill level : </label> <input type="text"
						class="form-control" id="skill" placeholder="Skill" name="skill"
						pattern="[a-zA-Z0-9]{3,15}" required="">
				</div>
				<br /> <br />
				<div class="form-group">
					<label for="domain">Enter Domain : </label> <input type="text"
						class="form-control" id="projId" placeholder="Domain"
						name="domain" pattern="[a-zA-Z0-9\s+]{2,20}" required="">
				</div>
				<br /> <br />
				<div class="form-group">
					<label for="vacancy">Enter Vacancy Name : </label> <input
						type="text" class="form-control" id="vacancy"
						placeholder="Vacancy Name" name="vacancy"
						pattern="[a-zA-Z0-9\s+]{2,30}" required="">
				</div>
				<br /> <br />

				<div class="form-group">
					<label for="number">Enter Number of Resource Required :</label> <input
						type="number" class="form-control" id="vacancy"
						placeholder="Number required" min=1 name="number" required="">
				</div>
				<br /> <br /> <input type="submit" class="btn btn-info"
					name="action" value="Raise Requisition" />
			</div>
			<!-- /card-container -->
		</div>

	</form>

	<h3>Project Details</h3>
	<table border="1px" align="center">
		<tr>
			<th>Project ID</th>
			<th>Project Name</th>
			<th>Project Description</th>
			<th>Project Start Date</th>
			<th>Project End Date</th>
			<th>Resource Manager ID</th>

		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
					ProjectBean prjBean = list.get(i);
		%>
		<tr>
			<td><%=prjBean.getProjectId()%></td>
			<td><%=prjBean.getProjectName()%></td>
			<td><%=prjBean.getProjectDesc()%></td>
			<td><%=prjBean.getProjectStartDate().substring(0,10)%></td>
			<td><%=prjBean.getProjectEndDate().substring(0,10)%></td>
			<td><%=prjBean.getProjectRmId()%></td>
		</tr>
		<%
			}
		%>

	</table>


	<%
		} else {
	%>
	<h2>
		<font color="red">No projects available</font>
	</h2>
	<%
		}
	%>

	<a href="#" onclick="history.go(-1)">Go Back </a>
</body>
</html>