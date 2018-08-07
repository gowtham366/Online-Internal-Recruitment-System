package com.recruitmentSystem.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recruitmentSystem.bean.EmployeeBean;
import com.recruitmentSystem.bean.ProjectBean;
import com.recruitmentSystem.bean.RequisitionBean;
import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.exception.OIRSException;
import com.recruitmentSystem.service.AdminService;
import com.recruitmentSystem.service.AuthenticateServiceImpl;
import com.recruitmentSystem.service.IAdminService;
import com.recruitmentSystem.service.IAuthenticateService;
import com.recruitmentSystem.service.IRMGEService;
import com.recruitmentSystem.service.IRMService;
import com.recruitmentSystem.service.RMGEServiceImpl;
import com.recruitmentSystem.service.RMServiceImpl;

/**
 * Servlet implementation class OIRSController
 */
@WebServlet("/OIRSController")
public class OIRSController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OIRSController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	IAdminService adminService;
	IRMService rmService;
	IRMGEService rmgeService;
	UserBean userBean;
	IAuthenticateService authService = new AuthenticateServiceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if(action!=null){
			//out.print(request.getServletPath()+" "+action);
			if(action.equalsIgnoreCase("Login")){
				String UserId = request.getParameter("userId");
				String userPassword = request.getParameter("userPassword");
				try {
					//userPassword = adminService.encryptPassword(userPassword);
					userBean = authService.loginUser(UserId, userPassword);
					if(userBean != null){

						String userRole = userBean.getUserRole();
						String lastLogin =  userBean.getLastLogin();
						System.out.println("Last login : "+lastLogin);

						HttpSession session = request.getSession(true);
						session.setAttribute("userId", userBean.getUserId());
						session.setAttribute("userRole", userBean.getUserRole());
						session.setAttribute("UserBean", userBean);
						session.setAttribute("logindate",lastLogin);
						response.setStatus(200);
						if(userRole.equalsIgnoreCase("admin")){
							adminService = new AdminService();
							response.setStatus(200);
							RequestDispatcher rd=request.getRequestDispatcher("/AdminPage.jsp");
							rd.include(request, response);
							//out.print("<font color=green>Login Success!!!</font>");
						}
						else if(userRole.equalsIgnoreCase("rm")){
							rmService = new RMServiceImpl();
							rmgeService = new RMGEServiceImpl();
							response.setStatus(200);
							RequestDispatcher rd=request.getRequestDispatcher("/RmPage.jsp");
							rd.include(request, response);
						}
						else if(userRole.equalsIgnoreCase("rmge")){
							rmgeService = new RMGEServiceImpl();
							rmService = new RMServiceImpl();
							response.setStatus(200);
							RequestDispatcher rd=request.getRequestDispatcher("/RmgePage.jsp");
							rd.include(request, response);
						}
					}
					else
					{
						response.setStatus(401);
						RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
						rd.include(request, response);
						out.print("<font color=red>Invalid login details</font>");
					}

				}catch (OIRSException e) {

					RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
					rd.include(request, response);
					out.print("<font color=red>Database Connection Failed</font>");
				}			

			}//action=login


			//logout
			else if(action.equalsIgnoreCase("logout")){

				HttpSession session=request.getSession(false);
				session.invalidate();
				RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
				rd.include(request, response);
				out.print("<font color=green>Logged out sucessfully!!</font>");

			}


			//redirect to add new user form page
			else if(action.equalsIgnoreCase("Add New User")){
				RequestDispatcher rd=request.getRequestDispatcher("/addUser.jsp");
				rd.forward(request, response);	
			}

			else if(action.equals("Add User"))
			{
				String userId = request.getParameter("userId");
				String userPassword = request.getParameter("userPassword");
				String userConfirmPassword = request.getParameter("userConfirmPassword");
				String userRole = request.getParameter("userRole").toLowerCase();
				String userHint = request.getParameter("userHint").toLowerCase();

				if(userPassword.equals(userConfirmPassword)){
					UserBean addUserBean = new UserBean();
					addUserBean.setUserId(userId);
					addUserBean.setUserRole(userRole);
					addUserBean.setHint(userHint);

					try {
						//System.out.println(adminService.encryptPassword(userPassword));
						//String encryptedPassword = adminService.encryptPassword(userPassword);
						//addUserBean.setUserPassword(encryptedPassword);
						addUserBean.setUserPassword(userPassword);
						String result = adminService.addNewUser(addUserBean);
						System.out.println(result);
						if(result == null){
							RequestDispatcher rd=request.getRequestDispatcher("/addUser.jsp");
							rd.include(request, response);
							out.print("<font color=red>User already exist</font>");
						}
						else{
							RequestDispatcher rd=request.getRequestDispatcher("/addUser.jsp");
							rd.include(request, response);
							out.print("<font color=green>User added succesfully!!!</font>");
						}
					} catch (OIRSException e) {
						RequestDispatcher rd=request.getRequestDispatcher("/addUser.jsp");
						rd.include(request, response);
						out.print("<font color=red>User already exist</font>");
					}					

				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("/addUser.jsp");
					rd.include(request, response);
					out.print("<font color=red>Password and Confirm Password must be same</font>");
				}


			}

			//Assign Role form
			else if(action.equals("Assign Role")){
				try {
					HttpSession session=request.getSession(false);
					List<UserBean> list = adminService.getUserDetails();
					session.setAttribute("userDetails", list);
					RequestDispatcher rd=request.getRequestDispatcher("/assignRole.jsp");
					rd.forward(request, response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			}

			//Assign Role
			else if(action.equals("Assign"))
			{
				HttpSession session=request.getSession(false);
				String assignUserId = request.getParameter("assignId");
				String assignRole = request.getParameter("assignRole");
				String existingRole = "";
				List<UserBean> userList = (List<UserBean>) session.getAttribute("userDetails");
				int flag = 0;
				for(UserBean bean : userList){
					if(bean.getUserId().equals(assignUserId) && !assignUserId.equals(session.getAttribute("userId")) && !assignUserId.equals("rmg")){
						existingRole = bean.getUserRole();
						//System.out.println(existingRole);
						if(existingRole.equals(assignRole)){
							flag = 1;		
							break;
						}
					}
				}
				if(flag == 0){
					try {
						String res = adminService.assignRole(assignUserId, assignRole);
						if(res==null){
							RequestDispatcher rd=request.getRequestDispatcher("/assignRole.jsp");
							rd.include(request, response);
							out.print("<font color=red>Failed to assign User Role</font>");
						}
						else
						{				
							try {
								List<UserBean> list = adminService.getUserDetails();
								session.setAttribute("userDetails", list);
								RequestDispatcher rd=request.getRequestDispatcher("/assignRole.jsp");
								rd.include(request, response);

								if(assignRole.equals("rm"))
									assignRole = "Resource Manager";
								else if(assignRole.equals("rmge"))
									assignRole = "Resource Management Group Executive";
								else if(assignRole.equals("admin"))
									assignRole = "Admin";

								if(existingRole.equals("rm"))
									existingRole = "Resource Manager";
								else if(existingRole.equals("rmge"))
									existingRole = "Resource Management Group Executive";
								else if(existingRole.equals("admin"))
									existingRole = "Admin";


								out.print("<font color=green>"+assignUserId+" Role changed from "+existingRole+" to "+assignRole+"</font>");
							} catch (OIRSException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							//out.println("succesfully assigned role");
						}
					} catch (OIRSException e) {
						System.out.println(e.getMessage());
					}

				}//if flag
				else{
					if(existingRole.equals("rm"))
						existingRole = "Resource Manager";
					else if(existingRole.equals("rmge"))
						existingRole = "Resource Management Group Executive";
					else if(existingRole.equals("admin"))
						existingRole = "Admin";
					RequestDispatcher rd=request.getRequestDispatcher("/assignRole.jsp");
					rd.include(request, response);
					out.print("<font color=red>"+assignUserId+" is already "+existingRole+"</font>");
				}

			}

			//Delete user form
			else if(action.equals("Delete User"))
			{
				HttpSession session=request.getSession(false);
				try {

					List<UserBean> list = adminService.getUserDetails();
					session.setAttribute("userDetails", list);
					RequestDispatcher rd=request.getRequestDispatcher("/DeleteUser.jsp");
					rd.forward(request, response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			}

			//Delete user
			else if(action.equals("Delete"))
			{
				HttpSession session=request.getSession(false);
				String deleteUserId = request.getParameter("deleteUserId");
				try {
					String res = adminService.deleteUser(deleteUserId);
					if(res==null){
						RequestDispatcher rd=request.getRequestDispatcher("/DeleteUser.jsp");
						rd.include(request, response);
						out.print("<font color=red>"+deleteUserId+" failed to delete</font>");		
					}
					else{
						List<UserBean> list = adminService.getUserDetails();
						session.setAttribute("userDetails", list);
						RequestDispatcher rd=request.getRequestDispatcher("/DeleteUser.jsp");
						rd.include(request, response);
						out.print("<font color=green>"+deleteUserId+" deleted Successfully!!!</font>");
					}
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}


			//Raise Requisition form
			else if(action.equals("RaiseRequisitions"))
			{
				HttpSession session = request.getSession(false);
				RequisitionBean reqBean = new RequisitionBean();
				String rmId = (String) session.getAttribute("userId");
				reqBean.setReqRmId(rmId);
				try {
					List<ProjectBean> prjBeanList = rmService.getProjectDetailsByStatus(rmId, "OPEN");
					request.setAttribute("projectDetails", prjBeanList);
					RequestDispatcher rd=request.getRequestDispatcher("/RaiseRequisition.jsp");
					rd.forward(request, response);
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}



			//Raise Requisition
			else if(action.equals("Raise Requisition")){
				HttpSession session = request.getSession(false);
				String rmId = (String) session.getAttribute("userId");
				String projId = request.getParameter("projId");
				String skill = request.getParameter("skill").toLowerCase().trim();
				String domain = request.getParameter("domain").toLowerCase().trim();
				String vacancy = request.getParameter("vacancy").trim();
				int numReq = Integer.parseInt(request.getParameter("number"));

				RequisitionBean reqBean = new RequisitionBean();
				reqBean.setReqRmId(rmId);
				reqBean.setReqProjectId(projId);
				reqBean.setReqSkill(skill);
				reqBean.setReqDomain(domain);
				reqBean.setReqVacancyName(vacancy);
				reqBean.setReqNoReq(numReq);

				String reqId=null;
				try {
					List<ProjectBean> prjBeanList = rmService.getProjectDetailsByStatus(rmId, "OPEN");
					request.setAttribute("projectDetails", prjBeanList);

					reqId = rmService.raiseRequisition(reqBean);
					//reqId = "raised";
					//reqId = null;
					if(reqId==null)
					{
						RequestDispatcher rd=request.getRequestDispatcher("/RaiseRequisition.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Failed to raise Requisitation')</script>");
						//out.print("<font color=red>Failed to Raise Requisition</font>");	
					}
					else{
						RequestDispatcher rd=request.getRequestDispatcher("/RaiseRequisition.jsp");
						rd.include(request, response);
						out.print("<script>window.alert("+"'Requisition Raised Succesfully with Requisition Id : "+reqId+"')</script>");
						//out.print("<font color=green>Requisition Raised Succesfully with Requisition Id : "+reqId+" </font>");	
					}
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					RequestDispatcher rd=request.getRequestDispatcher("/RaiseRequisition.jsp");
					rd.include(request, response);
					out.print("<script>window.alert('Failed to raise Requisitation')</script>");	
				}

			}//raise Requisition

			//Accept/Reject resource
			else if(action.equals("Accept/Reject")){
				HttpSession session = request.getSession(false);
				String rmId = (String) session.getAttribute("userId");
				System.out.println("Accept/Reject : "+rmId);
				try {
					List<RequisitionBean> reqDetails = rmgeService.getRequisitionByRMId(rmId);
					request.setAttribute("reqDetails", reqDetails);
					RequestDispatcher rd=request.getRequestDispatcher("/RMReqAcceptReject.jsp");
					rd.forward(request, response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			//Getting tagged employees
			else if(action.equals("getTaggedEmployees")){
				HttpSession session = request.getSession(false);
				String rmId = (String) session.getAttribute("userId");
				String reqId = request.getParameter("reqId");
				session.setAttribute("reqId", reqId);
				try {
					List<EmployeeBean> empList = rmService.viewRequestRes(reqId);	
					if(empList.size()>0){
						RequisitionBean reqBean = rmgeService.getRequisitionDetails(reqId);
						//session.setAttribute("prjId", reqBean.getReqProjectId());
						session.setAttribute("numReq", reqBean.getReqNoReq());
						session.setAttribute("numAssigned", reqBean.getNumAssigned());
						request.setAttribute("empDetails", empList);
						RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
						rd.forward(request, response);
					}
					else
					{
						List<RequisitionBean> reqDetails = rmgeService.getRequisitionByRMId(rmId);
						request.setAttribute("reqDetails", reqDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/RMReqAcceptReject.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('No Resource Assigned to this Requisition')</script>");
					}

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			//Accept Employees
			else if(action.equals("Accept Employee")){
				HttpSession session = request.getSession(false);
				String empIds[] = request.getParameterValues("empIds");
				String reqId = 	(String) session.getAttribute("reqId");
				System.out.println(reqId);
				boolean result = false;
				try {
					if(empIds!=null){
						for(String empId:empIds){
							System.out.println("Selected emp : "+empId);
							result = rmService.acceptRes(empId, reqId);
						}
						if(result){
							List<EmployeeBean> empList = rmService.viewRequestRes(reqId);
							request.setAttribute("empDetails", empList);
							RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
							rd.include(request, response);
							out.print("<script>window.alert('Resource(s) accepted!!!')</script>");
						}
						else {
							List<EmployeeBean> empList = rmService.viewRequestRes(reqId);
							request.setAttribute("empDetails", empList);
							RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
							rd.include(request, response);
							out.print("<script>window.alert('Error : Resource(s) not accepted')</script>");
						}

					}
					else{
						List<EmployeeBean> empList = rmService.viewRequestRes(reqId);
						request.setAttribute("empDetails", empList);
						RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Please select the employee to accept')</script>");			
					}
				} catch (OIRSException e) {
					// TODO Auto-generated catch block

					RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
					rd.include(request, response);
					out.print("<script>window.alert('Error Occured')</script>");
					e.printStackTrace();
				}

			}



			//Reject Employees
			else if(action.equals("Reject Employee")){
				HttpSession session = request.getSession(false);
				String empIds[] = request.getParameterValues("empIds");
				String reqId = 	(String) session.getAttribute("reqId");
				boolean result = false;
				try {
					if(empIds!=null){
						for(String empId:empIds){
							System.out.println("Selected emp : "+empId);
							result = rmService.rejectRes(empId, reqId);
						}
						//result = false;
						if(result){
							List<EmployeeBean> empList = rmService.viewRequestRes(reqId);
							request.setAttribute("empDetails", empList);
							RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
							rd.include(request, response);
							out.print("<script>window.alert('Selected Resource(s) Rejected!!!')</script>");
						}
						else {
							List<EmployeeBean> empList = rmService.viewRequestRes(reqId);
							request.setAttribute("empDetails", empList);
							RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
							rd.include(request, response);
							out.print("<script>window.alert('Error : Resource(s) not Rejected')</script>");
						}

					}
					else{
						List<EmployeeBean> empList = rmService.viewRequestRes(reqId);
						request.setAttribute("empDetails", empList);
						RequestDispatcher rd=request.getRequestDispatcher("/ViewRequisitionResponse.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Please select the employee to reject')</script>");			
					}
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", "Internal error occured... Try again");
					RequestDispatcher rd=request.getRequestDispatcher("/ErrorPage.jsp");
					rd.forward(request, response);
					//out.print("<script>window.alert('Error Occured')</script>");
					e.printStackTrace();
				}
			}//Reject employee

			else if(action.equals("viewRmProject")){
				HttpSession session = request.getSession(false);
				String rmId = (String) session.getAttribute("userId");
				List<ProjectBean> prjDetails;
				try {
					prjDetails = rmService.getProjectDetails(rmId);
					if(prjDetails.size()>0){
						request.setAttribute("prjDetails", prjDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/viewRMProject.jsp");
						rd.forward(request, response);	
					}
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("/RmPage.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Currently no project is available')</script>");
					}

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}

			//close project form
			else if(action.equals("closeRmProject")){
				HttpSession session = request.getSession(false);
				String rmId = (String) session.getAttribute("userId");
				List<ProjectBean> prjDetails;
				try {
					prjDetails = rmService.getProjectDetailsByStatus(rmId, "OPEN");
					if(prjDetails.size()>0){
						request.setAttribute("prjDetails", prjDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/closeUnasignProj.jsp");
						rd.forward(request, response);	
					}
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("/RmPage.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Currently no project is available')</script>");
					}

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			//close project
			else if(action.equals("closeProject")){
				HttpSession session = request.getSession(false);
				String rmId = (String) session.getAttribute("userId");
				String prjId = request.getParameter("prjId");
				boolean result = false;
				try {
					result = rmService.closeProject(prjId);
					if(result){
						List<ProjectBean> prjDetails = rmService.getProjectDetailsByStatus(rmId, "OPEN");
						if(prjDetails.size()>0){
							request.setAttribute("prjDetails", prjDetails);
							RequestDispatcher rd=request.getRequestDispatcher("/closeUnasignProj.jsp");
							rd.include(request, response);
							out.print("<script>window.alert('Project Closed Successfully!!!')</script>");	
						}
						else
						{
							RequestDispatcher rd=request.getRequestDispatcher("/RmPage.jsp");
							rd.include(request, response);
							out.print("<script>window.alert('Currently no project is available')</script>");
						}

					}
					else
					{
						List<ProjectBean> prjDetails = rmService.getProjectDetailsByStatus(rmId, "OPEN");
						request.setAttribute("prjDetails", prjDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/closeRmProject.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Project failed to close')</script>");
					}


				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//close project


			//unassign employee form 1
			else if(action.equals("unAssignEmployee")){
				HttpSession session = request.getSession(false);
				String rmId = (String) session.getAttribute("userId");
				List<ProjectBean> prjDetails;
				try {
					prjDetails = rmService.getProjectDetailsByStatus(rmId, "OPEN");
					if(prjDetails.size()>0){
						request.setAttribute("prjDetails", prjDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/unassignEmployeePrj.jsp");
						rd.forward(request, response);	
					}
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("/RmPage.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Currently no project is available')</script>");
					}

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			//unassign employee form 2
			else if(action.equals("showEmployeeDetails")){

				HttpSession session = request.getSession(false);
				String prjId = request.getParameter("prjId");
				session.setAttribute("prjId", prjId);
				String rmId = (String) session.getAttribute("rmId");
				session.setAttribute("rmId", rmId);
				try {
					List<EmployeeBean> empDetails = rmService.getEmpDetailsByPrjId(prjId);
					if(empDetails.size()>0){
						request.setAttribute("empDetails", empDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/unassignEmployee.jsp");
						rd.forward(request, response);	
					}
					else{
						List<ProjectBean> prjDetails = rmService.getProjectDetailsByStatus(rmId, "OPEN");
						request.setAttribute("prjDetails", prjDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/unassignEmployeePrj.jsp");
						rd.include(request, response);	
						out.print("<script>window.alert('Currently no employee is tagged to this project')</script>");
					}

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if(action.equals("unassignEmployeeEmpId")){
				HttpSession session = request.getSession(false);
				String prjId = (String) session.getAttribute("prjId");
				String rmId = (String) session.getAttribute("userId");
				String empId = request.getParameter("empId");
				try {
					boolean result = rmService.unassignProject(empId);
					if(result){
						List<EmployeeBean> empDetails = rmService.getEmpDetailsByPrjId(prjId);
						if(empDetails.size()>0){
							request.setAttribute("empDetails", empDetails);
							RequestDispatcher rd=request.getRequestDispatcher("/unassignEmployee.jsp");
							rd.include(request, response);	
							out.print("<script>window.alert('Employee unassigned successfully!!!')</script>");
						}
						else{
							List<ProjectBean> prjDetails = rmService.getProjectDetailsByStatus(rmId, "OPEN");
							request.setAttribute("prjDetails", prjDetails);
							RequestDispatcher rd=request.getRequestDispatcher("/unassignEmployeePrj.jsp");
							rd.include(request, response);
							out.print("<script>window.alert('Currently no employee is tagged to this project')</script>");
						}	
					}
					else
					{
						List<EmployeeBean> empDetails = rmService.getEmpDetailsByPrjId(prjId);
						request.setAttribute("empDetails", empDetails);
						RequestDispatcher rd=request.getRequestDispatcher("/unassignEmployee.jsp");
						rd.include(request, response);	
						out.print("<script>window.alert('Failed to unassign Employee')</script>");
					}
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


			//View All Requisition

			else if(action.equals("ViewAllRequisitions"))
			{
				HttpSession session = request.getSession(false);
				//List<RequisitionBean> reqBeanList;
				try {
					List<RequisitionBean> reqBeanList = rmgeService.getAllRequisitions();
					if(reqBeanList.size()>0){
						request.setAttribute("reqDetails", reqBeanList);
						RequestDispatcher rd=request.getRequestDispatcher("/allRequisitions.jsp");
						rd.forward(request, response);	
					}
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("/RmgePage.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('No requisition available')</script>");
					}

				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}

			//Search EMployee

			else if(action.equals("searchEmployee"))
			{
				HttpSession session = request.getSession(false);
				//String rmgeId = (String) session.getAttribute("userId");
				RequisitionBean reqBean = new RequisitionBean();
				String reqId = request.getParameter("reqId");
				session.setAttribute("reqId", reqId);
				String domain = request.getParameter("domain");
				String skill = request.getParameter("skill");
				String numReq = request.getParameter("numReq");
				String numAssigned = request.getParameter("numAssigned");
				session.setAttribute("numReq", numReq);
				session.setAttribute("numAssigned", numAssigned);
				reqBean.setReqId(reqId);
				reqBean.setReqDomain(domain);
				reqBean.setReqSkill(skill);
				session.setAttribute("reqBean", reqBean);
				try {
					List<EmployeeBean> empList = rmgeService.searchEmployee(reqBean);
					if(empList.size()>0){
						session.setAttribute("numReq", numReq);
						session.setAttribute("numAssigned", numAssigned);
						request.setAttribute("empDetails", empList);
						RequestDispatcher rd=request.getRequestDispatcher("/searchEmployee.jsp");
						rd.forward(request, response);	
					}else{
						List<RequisitionBean> reqBeanList = rmgeService.getAllRequisitions();
						request.setAttribute("reqDetails", reqBeanList);
						RequestDispatcher rd=request.getRequestDispatcher("/allRequisitions.jsp");
						rd.include(request, response);	
						out.print("<script>window.alert('No employee matched matched for the requirement')</script>");
					}

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			


			//Assign Employee
			else if(action.equals("Assign Employee(s)")){
				HttpSession session = request.getSession(false);
				String empIds[] = request.getParameterValues("empIds");
				String reqId = 	(String) session.getAttribute("reqId");
				System.out.println(reqId);
				boolean result = false;
				try {
					if(empIds!=null){
						for(String empId:empIds){
							System.out.println("Selected emp : "+empId);
							result = rmgeService.assignProject(empId, reqId);
						}
						if(result){
							String numAssigned = (String) session.getAttribute("numAssigned");
							int numAssign = Integer.valueOf(numAssigned)+empIds.length;
							RequisitionBean reqBean = new RequisitionBean();
							reqBean = rmgeService.updateReqNumberAssigned(reqId, numAssign);
							session.setAttribute("numReq", reqBean.getReqNoReq());
							session.setAttribute("numAssigned", String.valueOf(reqBean.getNumAssigned()));				
							List<EmployeeBean> empList = rmgeService.searchEmployee(reqBean);
							if(empList.size()>0){
								request.setAttribute("empDetails", empList);
								RequestDispatcher rd=request.getRequestDispatcher("/searchEmployee.jsp");
								rd.include(request, response);
								out.print("<script>window.alert('Resource(s) Assigned!!!')</script>");	
							}else{
								List<RequisitionBean> reqBeanList = rmgeService.getAllRequisitions();
								request.setAttribute("reqDetails", reqBeanList);
								RequestDispatcher rd=request.getRequestDispatcher("/allRequisitions.jsp");
								rd.include(request, response);	
								out.print("<script>window.alert('Assigned all the employee that are matched')</script>");
							}
						}
						else {
							RequisitionBean reqBean = (RequisitionBean) session.getAttribute("reqBean");
							List<EmployeeBean> empList = rmgeService.searchEmployee(reqBean);
							if(empList.size()>0){
								request.setAttribute("empDetails", empList);
								RequestDispatcher rd=request.getRequestDispatcher("/searchEmployee.jsp");
								rd.include(request, response);
								out.print("<script>window.alert('Error : Failed to assign Resource(s)')</script>");
							}

						}

					}
					else{
						RequisitionBean reqBean = (RequisitionBean) session.getAttribute("reqBean");
						List<EmployeeBean> empList = rmgeService.searchEmployee(reqBean);
						request.setAttribute("empDetails", empList);
						RequestDispatcher rd=request.getRequestDispatcher("/searchEmployee.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('Please select the employee to assign')</script>");			
					}
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			//view requisition by RM ID
			else if(action.equals("viewRequisitionByRM")){
				try {
					List<String> rmIds = rmgeService.getRmIds();
					request.setAttribute("rmIds", rmIds);
					RequestDispatcher rd=request.getRequestDispatcher("/viewRMReqByRMId.jsp");
					rd.forward(request, response);		
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			//
			else if(action.equals("Get Raised Requisition")){
				HttpSession session = request.getSession(false);
				String rmId = request.getParameter("rmId");
				session.setAttribute("rmId", rmId);
				try {
					List<RequisitionBean> reqBeanList = rmgeService.getRequisitionByRMId(rmId);
					if(reqBeanList.size()>0){
						request.setAttribute("reqDetails", reqBeanList);
						RequestDispatcher rd=request.getRequestDispatcher("/RMRequisitions.jsp");
						rd.forward(request, response);	
					}
					else
					{
						RequestDispatcher rd=request.getRequestDispatcher("/RmgePage.jsp");
						rd.include(request, response);
						out.print("<script>window.alert('No requisition available')</script>");
					}

				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}

			//


			else if(action.equals("searchEmployee2"))
			{
				HttpSession session = request.getSession(false);
				//String rmgeId = (String) session.getAttribute("userId");
				RequisitionBean reqBean = new RequisitionBean();
				String reqId = request.getParameter("reqId");
				session.setAttribute("reqId", reqId);
				String domain = request.getParameter("domain");
				String skill = request.getParameter("skill");
				String numReq = request.getParameter("numReq");
				String numAssigned = request.getParameter("numAssigned");
				session.setAttribute("numReq", numReq);
				session.setAttribute("numAssigned", numAssigned);
				reqBean.setReqId(reqId);
				reqBean.setReqDomain(domain);
				reqBean.setReqSkill(skill);
				session.setAttribute("reqBean", reqBean);
				try {
					List<EmployeeBean> empList = rmgeService.searchEmployee(reqBean);
					if(empList.size()>0){
						session.setAttribute("numReq", numReq);
						session.setAttribute("numAssigned", numAssigned);
						request.setAttribute("empDetails", empList);
						RequestDispatcher rd=request.getRequestDispatcher("/searchEmployee.jsp");
						rd.forward(request, response);	
					}else{
						String rmId = (String) session.getAttribute("rmId");
						List<RequisitionBean> reqBeanList = rmgeService.getRequisitionByRMId(rmId);
						request.setAttribute("reqDetails", reqBeanList);
						RequestDispatcher rd=request.getRequestDispatcher("/RMRequisitions.jsp");
						rd.include(request, response);	
						out.print("<script>window.alert('No employee matched matched for the requirement')</script>");
					}

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			




			//Report Generation

			//Admin Report 
			else if(action.equals("generateAdminReport"))
			{
				response.setContentType("text/csv");
				response.setHeader("Content-Disposition", "attachment; filename=AdminReport.xls");
				try {
					List<UserBean> userList = adminService.getUserDetails();
					out.print("<table border=\"1\" cellpadding=\"3\" bordercolor='black'");
					out.print("<tr><th>User Id</th><th>Role</th><th>Last Login</th></tr>");
					for (UserBean userBean : userList) {
						out.print("<tr>");
						out.print("</td><td>"+userBean.getUserId()+"</td><td>"+userBean.getUserRole()+"</td><td>"+userBean.getLastLogin());
						out.print("</td></tr>");
					}
					out.print("</table>");
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}


			//RMGE Report
			else if(action.equals("generateRMGEReport"))
			{
				response.setContentType("text/csv");
				response.setHeader("Content-Disposition", "attachment; filename=RMGEReport.xls");
				try {
					List<RequisitionBean> reqList = rmgeService.getAllRequisitions();
					out.print("<table border=\"1\" cellpadding=\"3\" bordercolor='black'");
					out.print("<tr><th>Requisition Id</th><th>RM Id</th><th>Project Id</th><th>Date Created</th><th>Date Closed</th><th>Vacancy Name");
					out.print("</th><th>Skill</th><th>Domain</th><th>Number Required</th><th>Number Assigned</th></tr>");
					for (RequisitionBean reqBean : reqList) {
						out.print("<tr>");
						out.print("<td>"+reqBean.getReqId()+"</td><td>"+reqBean.getReqRmId()+"</td><td>"+reqBean.getReqProjectId()+"</td><td>"+reqBean.getReqDateCreated()+"</td><td>"+reqBean.getReqDateClosed());
						out.print("</td><td>"+reqBean.getReqVacancyName()+"</td><td>"+reqBean.getReqSkill()+"</td><td>"+reqBean.getReqDomain()+"</td><td>"+reqBean.getReqNoReq()+"</td><td>"+reqBean.getNumAssigned());
						out.print("</tr>");
					}
					out.print("</table>");
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}



			//RM Report

			else if(action.equals("generateRMReport"))
			{
				RequestDispatcher rd=request.getRequestDispatcher("/generateRMReport.jsp");
				rd.forward(request, response);
			}


			else if(action.equals("Generate"))
			{
				HttpSession session = request.getSession(false);
				response.setContentType("text/csv");
				response.setHeader("Content-Disposition", "attachment; filename=RMReport.xls");
				String status = request.getParameter("Status").toUpperCase();
				String rmId = (String) session.getAttribute("userId");
				IRMService rmService = new RMServiceImpl();
				try {
					List<ProjectBean> list;
					if(status.equals("ALL")){
						list = rmService.getProjectDetails(rmId);
					}
					else{
						list = rmService.getProjectDetailsByStatus(rmId, status);
					}

					out.print("<table border=\"1\" cellpadding=\"3\" bordercolor='black'");
					out.print("<tr><th>Project_Id</th><th>Project Name</th><th>Description</th><th>Start Date</th><th>End Date</th><th>RM Id</th><th>Status</th></tr>");
					for (ProjectBean projBean : list) {
						out.print("<tr>");
						out.print("<td>"+projBean.getProjectId()+"</td><td>"+projBean.getProjectName()+"</td><td>"+projBean.getProjectDesc()+"</td><td>"+projBean.getProjectStartDate()+"</td><td>"+projBean.getProjectEndDate()+"</td><td>"+projBean.getProjectRmId()+"</td><td>"+projBean.getProjectStatus());
						out.print("</tr>");
					}
					out.print("</table>");


					List<RequisitionBean> reqList = rmService.getRequisitionDetails(rmId);

					out.print("<table border=\"1\" cellpadding=\"3\" bordercolor='black'");
					out.print("<tr><th>Requisition Id</th><th>RM Id</th><th>Project Id</th><th>Date Created</th><th>Date Closed</th><th>Vacancy Name");
					out.print("</th><th>Skill</th><th>Domain</th><th>Status</th><th>Number Required</th><th>Number Assigned</th></tr>");
					for (RequisitionBean reqBean : reqList) {
						System.out.println(reqBean.getReqSkill());
						out.print("<tr>");
						out.print("<td>"+reqBean.getReqId()+"</td><td>"+reqBean.getReqRmId()+"</td><td>"+reqBean.getReqProjectId()+"</td><td>"+reqBean.getReqDateCreated()+"</td><td>"+reqBean.getReqDateClosed());
						out.print("</td><td>"+reqBean.getReqVacancyName()+"</td><td>"+reqBean.getReqSkill()+"</td><td>"+reqBean.getReqDomain()+"</td><td>"+reqBean.getReqStatus()+"</td><td>"+reqBean.getReqNoReq()+"</td><td>"+reqBean.getNumAssigned());
						out.print("</tr>");
					}
					out.print("</table>");
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			//end of report generation


		}//action!=null
		else{
			//request.getServletPath();
			RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
			out.print("<font color=red>Something went wrong!!!</font>");
		}

	}

}