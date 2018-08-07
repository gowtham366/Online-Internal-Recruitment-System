<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate RM Report</title>
</head>
<body class="text-center">
	<h3>Generate Report</h3>
	<br />
	<form class="form-inline" method="post" action="OIRSController">
		<div class="form-group">
			<div class="container">
				<div class="card card-container">
					<br /></br>

					<div class="form-group" class="text-center">
						<label for="role"> Select the Status of the project:</label> <select
							name="Status" class="form-control" required="">
							<option value="" selected disabled>Please Select</option>
							<option value="Open">Opened Projects</option>
							<option value="Closed">Closed Projects</option>
							<option value="All">All Projects</option>
						</select>
					</div>
					<br /> <br /> <br /> <input type="submit" class="btn btn-info"
						name="action" value="Generate" />
				</div>
				<!-- /card-container -->
			</div>
		</div>
	</form>
</body>
</html>