<%@page import="java.util.ArrayList"%>
<%@page import="com.recruitmentSystem.bean.RequisitionBean"%>
<%@page import="java.util.List"%>
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
<title>Requisition Raised</title>
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

	<br /></br>
	<h3>Requisitions Available</h3>
	<br />
	<br />
	<br />
	<br />
	<%
				List<RequisitionBean> list = (ArrayList<RequisitionBean>) request.getAttribute("reqDetails");
			%>
	</br>
	</br>

	<table border="1px" align="center">

		<tr>
			<th>Requisition ID</th>
			<th>Requested RM</th>
			<th>Project ID</th>
			<th>Project Name</th>
			<th>Vacancy Name</th>
			<th>Domain</th>
			<th>Skill</th>
			<th>Resource Required</th>
			<th>Resource Assigned</th>
			<th>Date Raised</th>
			<th>Date Closed</th>
			<th></th>
		</tr>
		<%
					for (int i = 0; i < list.size(); i++) {
						RequisitionBean reqBean = list.get(i);
				%>
		<tr>
			<td><%=reqBean.getReqId()%></td>
			<td><%=reqBean.getReqRmId()%></td>
			<td><%=reqBean.getReqProjectId()%></td>
			<td><%=reqBean.getReqProjectName()%></td>
			<td><%=reqBean.getReqVacancyName()%></td>
			<td><%=reqBean.getReqDomain()%></td>
			<td><%=reqBean.getReqSkill()%></td>
			<td><%=reqBean.getReqNoReq()%></td>
			<td><%=reqBean.getNumAssigned()%></td>
			<td><%=reqBean.getReqDateCreated().substring(0, 10)%></td>
			<%
						String endDate = reqBean.getReqDateClosed();
							if (endDate == null)
								endDate = "Not Closed";
							else
								endDate = endDate.substring(0, 10);
					%>
			<td><%=endDate%></td>
			<td><a
				href="./OIRSController?action=searchEmployee2&reqId=<%=reqBean.getReqId()%>&domain=<%=reqBean.getReqDomain()%>&skill=<%=reqBean.getReqSkill()%>&numReq=<%=reqBean.getReqNoReq()%>&numAssigned=<%=reqBean.getNumAssigned() %>">
					<input type="button" value="Search Employee"
					class="btn btn-info btn-lg">
			</a></td>
		</tr>
		<%
					}
				%>
	</table>
	<a href="#" onclick="history.go(-1)">Go Back </a>
</body>
</html>