<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.recruitmentSystem.bean.EmployeeBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style type="text/css">
  font
  {
  vertical-align:center;
  } 
.card 
	{
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
   .wrapper 
   {
    text-align: center;
	}
.card-container.card 
{
    max-width: 500px;
    padding: 40px 40px;
}


   .glyphicon{
   font-size: 25px;
   }
   
  .navbar-nav{
   font-size: 25px;
   }

</style>
<title>View Requisition Response</title>
</head>
<body  class="text-center">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <ul class="nav navbar-nav navbar-center">
   <center> <p><font size="6" color="#ffffff">ONLINE INTERNAL RECRUITEMENT SYSTEM</font></p></center>
      <!--  <p style="font-size:30px" class="navbar-text">Some text</p> -->
     
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="./OIRSController?action=logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>


	<%
		List<EmployeeBean> list = (ArrayList<EmployeeBean>) request.getAttribute("empDetails");
	%>
	<form class="form-inline" method="post" action="OIRSController">



		<div class="form-group">

			<h3>Employees Assigned by RMGE</h3>
	<br>
			<h4>Number of Resource Requested : ${numReq}</h4> 
			<h4>Number of Resource Assigned : ${numAssigned}</h4>
			</br>
	<div class="container">
    <div class="card card-container">
			<table border="1px">

				<tr>
					<th>Select the Employee(s)</th>
					<th>Employee ID</th>
					<th>Employee Name</th>
					<th>Employee Skill</th>
					<th>Employee Domain</th>
					<th>Employee Experience Years</th>
				</tr>
				<%
					for (int i = 0; i < list.size(); i++) {
							EmployeeBean empBean = list.get(i);
				%>
				<tr>
					<td align="center"><input type="checkbox" name="empIds"
						value="<%=empBean.getEmpId()%>"></td>
					<td><%=empBean.getEmpId()%></td>
					<td><%=empBean.getEmpName()%></td>
					<td><%=empBean.getEmpSkill()%></td>
					<td><%=empBean.getEmpDomain()%></td>
					<td><%=empBean.getEmpExp()%></td>
				</tr>
				<%
					}	
				%>
			</table>

			
	</br></br>
		<input type="submit" class="btn btn-info" name="action" value="Accept Employee" /> <br /></br>
			 <input type="submit" class="btn btn-info" name="action" value="Reject Employee" /> <br /></br>
</div><!-- /card-container -->
    </div>
    
</form>
<a href="#" onclick="history.go(-1)" >Go Back </a>  
</body>
</html>