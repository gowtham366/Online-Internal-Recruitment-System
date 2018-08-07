<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, com.recruitmentSystem.bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<style>
.navbar-default {
	background-color: #66c2ff;
	border-color: #000000;
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
	max-width: 350px;
	padding: 40px 40px;
}

.glyphicon {
	font-size: 25px;
}

.navbar-nav {
	font-size: 25px;
}

span {
	color: red;
}
</style>
<title>Delete User</title>
</head>
<body class="text-center">
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">

		<ul class="nav navbar-nav navbar-center">
			<p>
				<font size="5" align="center" color="#ffffff">Online Internal
					Recruitment System</font>
			</p>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="./OIRSController?action=Add New User"><span
					name="Add New User"></span><font size="3" align="center"
					color="#ffffff"> Add New User</font></a></li>&nbsp;
			<li><a href="./OIRSController?action=Assign Role"><span
					name="Assign Role"></span><font size="3" align="center"
					color="#ffffff"> Assign Role</font></a></li>&nbsp;
			<li><a href="./OIRSController?action=generateAdminReport"><span
					name="Generate User Report"></span><font size="3" align="center"
					color="#ffffff"> Generate Report</font></a></li>&nbsp;
			<li><a href="./OIRSController?action=logout"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
	</nav>
	<br />
	<h3>Admin Deleting User</h3>

	<%
		String userId = (String) session.getAttribute("userId");
		List<UserBean> list = (ArrayList<UserBean>) session.getAttribute("userDetails");
	%>
	<%
		if (list.size() > 0) {
	%>
	<form class="form-inline" method="post" action="OIRSController">
		<div class="form-group">
			<div class="container">
				<div class="card card-container">
					<div class="form-group">
						<label for="role"> Select User ID <span>*</span></label> <select
							name="deleteUserId" required="" class="form-control">
							<%
								for (int i = 0; i < list.size(); i++) {
										UserBean userBean = list.get(i);
										if (!userId.equals(userBean.getUserId())) {
							%>
							<Option value="<%=userBean.getUserId()%>"><%=userBean.getUserId()%></Option>
							<%
								}
									}
							%>
						</select>

					</div>

					<br /></br> <input type="submit" class="btn btn-info" name="action"
						value="Delete" /> </br>
					</br>
					<tr>
						<td>
						<td colspan="2" align="center"><input type="reset"
							class="btn btn-info" name="action" value="Reset" /> <input
							action="action" class="btn btn-danger"
							onclick="window.history.go(-1); return false;" type="button"
							value="Cancel" />
				</div>
				<!-- /card-container -->
			</div>
	</form>
	<%
		} else {
	%>
	<h2>
		<font color="red">No user available</font>
	</h2>
	<%
		}
	%>

	<br />
	<br />
</body>
</html>