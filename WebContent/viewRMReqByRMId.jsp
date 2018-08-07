<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RMGE</title>
</head>
<body class="text-center">
	<h3>Requisition Available</h3>
	<br />
	<%
		List<String> list = (ArrayList<String>) request.getAttribute("rmIds");
	%>
	<form class="form-inline" method="post" action="OIRSController">
		<div class="form-group">
			<div class="container">
				<div class="card card-container">
					<br /></br>

					<div class="form-group" class="text-center">
						<label for="role"> Select Resource Manager :</label> <select
							name="rmId" class="form-control" required="">
							<option value="" selected disabled>Please Select</option>
							<%
								for (String rmId : list) {
							%>
							<option value="<%=rmId%>"><%=rmId%></option>
							<%
								}
							%>
						</select>
					</div>
					<br /> <br /> <br /> <input type="submit" class="btn btn-info"
						name="action" value="Get Raised Requisition" />
				</div>
				<!-- /card-container -->
			</div>
		</div>
	</form>
</body>
</html>