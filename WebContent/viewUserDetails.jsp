<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,com.recruitmentSystem.bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
		List<UserBean> list = (ArrayList<UserBean>) request.getAttribute("userDetails");
	%>
	<table>

		<%
			if (list.size() > 0) {
				String userId = (String) session.getAttribute("userId");
		%>
		<tr>
			<th>User ID</th>
			<th>Role</th>
			<th>Last Login</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
					UserBean userBean = list.get(i);
					if (userBean.getUserId() != userId) {
		%>
		<tr>
			<td><%=userBean.getUserId()%></td>
			<td><%=userBean.getUserRole()%></td>
			<td><%=userBean.getLastLogin()%></td>
		</tr>
		<%
			}
				}
			} else {
		%>
		<td colspan="5" align="center">No user available</td>
		<%
			}
		%>
	</table>
</body>
</html>