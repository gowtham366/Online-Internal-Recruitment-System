<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<style>
.navbar-default {
	background-color: #404040;
	border-color: #000000;
}

#nav p {
	text-align: center;
}

.card {
	background-color: #D3D3D3;
	/* just in case there no content*/
	padding: 20px 25px 30px;
	margin: 0 auto 25px;
	margin-top: 30px;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.card-container.card {
	max-width: 550px;
	padding: 40px 40px;
}

.glyphicon {
	font-size: 25px;
}

.navbar-nav {
	font-size: 25px;
}
</style>
<title>Add User</title>
</head>
<body class="text-center">

	<nav class="navbar navbar-default">
	<div class="container-fluid">

		<ul class="nav navbar-nav navbar-center">
			<p>
				<font size="5" color="#ffffff">Online Internal Recruitment
					System</font>
			</p>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="./OIRSController?action=Assign Role"><span
					name="Assign Role"></span><font size="3" align="center"
					color="#ffffff"> Assign Role</font></a></li>&nbsp;
			<li><a href="./OIRSController?action=Delete User"><span
					name="Delete User"></span><font size="3" align="center"
					color="#ffffff"> Delete User</font></a></li>&nbsp;
			<li><a href="./OIRSController?action=generateAdminReport"><span
					name="Generate User Report"></span><font size="3" align="center"
					color="#ffffff">Generate Report</font></a></li>&nbsp;
			<li><a href="./OIRSController?action=logout"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
	</nav>
	<h3>Add New User</h3>
	<center>
		<div class="container">
			<div class="card card-container">
				<table cellpadding="10">
					<form class="form-inline" method="post" action="OIRSController">

						<div class="col-md-4 col-md-offset-4">

							<tr>
								<td><div class="form-group">
										<td><label for="UserId">Enter User Id <span>*</span></td>
										<td><input type="text" class="form-control" id="Id"
											placeholder="Enter Id for User" name="userId"
											pattern="[a-zA-Z0-9]{3,15}" title="Example: user1"
											required="">
							</tr>
							</label>
						</div>
						</td>
						</tr>

						<tr>
							<td><div class="form-group"></div></td>
						</tr>

						<tr>
							<td><div class="form-group">
									<td><label for="pwd">Password<span>*</span></td>
									<td><input type="password" class="form-control" id="pwd"
										placeholder="Enter password" minlength="5" maxlength="16"
										name="userPassword" title="Password length must be 5 - 16"></td>
									</label>
								</div></td>
						</tr>
						<tr>
							<td><div class="form-group"></div></td>
						</tr>
						<tr>
							<td><div class="form-group">
									<td><label for="pwd">Confirm Password<span>*</span></td>
									<td><input type="password" class="form-control" id="pwd"
										placeholder="Enter password again" minlength="5"
										maxlength="16" name="userConfirmPassword"
										title="Password length must be 5 - 16"></td> </label>
								</div></td>
						</tr>

						<tr>
							<td><div class="form-group"></div></td>
						</tr>

						<tr>
							<td><div class="form-group">
									<td><label for="role">Select user role <span>*</span></td>
									<td><select name="userRole" class="form-control"
										required="">
											<option value="" selected disabled>Please Select</option>
											<option value="admin">Admin</option>
											<option value="rm">Resource Manager</option>
											<option value="rmge">Resource Manager Executive</option>
									</select></td>
									</label>
								</div></td>
						</tr>

						<tr>
							<td><div class="form-group"></div></td>
						</tr>

						<tr>
							<td><div class="form-group">
									<td><label for="hint">Enter hint for the password
											<span>*</span></td>
									<td><input type="text" class="form-control" id="pwd"
										placeholder="Enter any hint" name="userHint"
										pattern="[a-zA-Z]{3,15}" required=""></td> </label>
								</div></td>
						</tr>
						<br />
						<br />

						<tr>
							<td><div class="form-group"></div></td>
						</tr>
						<div class="wrapper">
							<tr>
								<td>
								<td colspan="2" align="center"><input type="submit"
									class="btn btn-info" name="action" value="Add User" /></td>
								</td>
							</tr>
						</div>
						<tr>
							<td><div class="form-group"></div></td>
						</tr>

						<br />
						<br />
						<div class="wrapper">
							<tr>
								<td>
								<td colspan="2" align="center"><input type="reset"
									class="btn btn-info" name="action" value="Reset" />
						</div>


						<!--  <input type="button" class="btn btn-info" value = "Cancel" onclick="AdminPage.jsp"/> -->
						<input type="button" action="action" class="btn btn-danger"
							onclick="window.history.go(-1); return false;" value="Cancel" />

						</div>
				</table>
				</form>
	</center>
</body>
</html>