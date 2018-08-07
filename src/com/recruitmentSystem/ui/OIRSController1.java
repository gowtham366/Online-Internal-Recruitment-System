package com.recruitmentSystem.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
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
import com.recruitmentSystem.dao.RMGEDAOImpl;
import com.recruitmentSystem.exception.OIRSException;
import com.recruitmentSystem.service.AdminService;
import com.recruitmentSystem.service.AuthenticateServiceImpl;
import com.recruitmentSystem.service.IAdminService;
import com.recruitmentSystem.service.IAuthenticateService;
import com.recruitmentSystem.service.IRMGEService;
import com.recruitmentSystem.service.IRMService;
import com.recruitmentSystem.service.RMGEServiceImpl;
import com.recruitmentSystem.service.RMServiceImpl;
import com.sun.xml.internal.ws.api.server.InstanceResolverAnnotation;

/**
 * Servlet implementation class OIRSController
 */
@WebServlet("/OIRSController1")
public class OIRSController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public OIRSController1() {
		super();
		// TODO Auto-generated constructor stub
	}
	UserBean userbean;
	//HttpSession session;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("action");
		HttpSession session = request.getSession(false);
		if(option.equals("logout")){
			if(session!=null){
				System.out.println(session.isNew());
				session.invalidate();	
			}
			response.sendRedirect("./login.html");
		}

	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		IAdminService adminService = new AdminService();
		IRMGEService iRMGEService = new RMGEServiceImpl();
		String option = request.getParameter("action");
		int RMGEnumReq = 0; 
		if(option.equals("Log In")){
			String UserId = request.getParameter("userId");
			String userPassword = request.getParameter("userPassword");
			IAuthenticateService authService = new AuthenticateServiceImpl();
			try {
				userbean = authService.loginUser(UserId, userPassword);
				if(userbean != null){
					response.setStatus(200);
					HttpSession session = request.getSession(true);
					//session = request.getSession(false);
					//session.setMaxInactiveInterval(7);
					String beanRole = userbean.getUserRole();
					String date =  userbean.getLastLogin();
					session.setAttribute("userId", userbean.getUserId());
					session.setAttribute("UserBean", userbean);
					session.setAttribute("logindate", userbean.getLastLogin());
					System.out.println("Last login : "+date);
					if(beanRole.equals("admin")){
						//						request.setAttribute("UserBean", userbean);
						//						request.setAttribute("logindate", userbean.getLastLogin());
						getServletContext().getRequestDispatcher("/AdminPage.jsp").forward(request,response);
					}
					else if(beanRole.equals("rm")){
						//request.setAttribute("UserBean", userbean);
					
						getServletContext().getRequestDispatcher("/RmPage.jsp").forward(request,response);
					}
					else if(beanRole.equals("rmge"))
					{
						//request.setAttribute("UserBean", userbean);
						getServletContext().getRequestDispatcher("/RmgePage.jsp").forward(request,response);
					}
				}
				else
				{
					response.setStatus(401);
					//response.sendError(response.SC_UNAUTHORIZED, "Invalid User name/Password");
					request.setAttribute("error","Invalid user name/Password");
					out.print("<p>Invalid User name/Password</p>");
					getServletContext().getRequestDispatcher("/index.jsp").include(request,response);
				}
				//out.println("Invalid UserName or Password");	
			}catch (OIRSException e) {}


		}
		else if(option.equals("Add New User"))
		{
			
			getServletContext().getRequestDispatcher("/addUser.jsp").forward(request,response);
		}
		else if(option.equals("Assign Role"))
		{
			try {
				List<String> list = adminService.getUserIds();
				request.setAttribute("userIds", list);
				getServletContext().getRequestDispatcher("/assignRole.jsp").forward(request,response);
			} catch (OIRSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
		else if(option.equals("Delete User"))
		{
			List<String> list;
			try {
				list = adminService.getUserIds();
				request.setAttribute("userIds", list);
				getServletContext().getRequestDispatcher("/DeleteUser.jsp").forward(request,response);
			} catch (OIRSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if(option.equals("Add User"))
		{
			System.out.println("Add new user");
			String newUserId = request.getParameter("newUserId");
			String newpswd = request.getParameter("newPwd");
			String newRole = request.getParameter("userRole").toLowerCase();
			String newHint = request.getParameter("userHint").toLowerCase();
			
			System.out.println(newUserId);
			System.out.println(newpswd);
			System.out.println(newRole);
			System.out.println(newHint);
			
			UserBean addUserBean = new UserBean();
			addUserBean.setUserId(newUserId);
			addUserBean.setUserPassword(newpswd);
			addUserBean.setUserRole(newRole);
			addUserBean.setHint(newHint);
			try {
				String result = adminService.addNewUser(addUserBean);
				System.out.println(result);
				if(result == null){
					out.println("Details Not Inserted");
					//go back
				}
				else{
					out.println("User added succesfully");
					//go back
					//getServletContext().getRequestDispatcher("/addUser.jsp").forward(request,response);

				}
			} catch (OIRSException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(option.equals("Assign"))
		{
			String assignUserId = request.getParameter("assignId");
			String assignRole = request.getParameter("assignRole");
			//userbean.setUserId(assignUserId);
			//userbean.setUserRole(assignRole);
			try {
				String res = adminService.assignRole(assignUserId, assignRole);
				if(res==null){
					out.println("User not found!!! Enter the valid user ID");
				}
				else
				{				
					try {
						List<String> list = adminService.getUserIds();
						request.setAttribute("userIds", list);
						getServletContext().getRequestDispatcher("/assignRole.jsp").include(request,response);
						out.print("<h2>User role assigned successfully!!</h2><br>");
						
					} catch (OIRSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					//out.println("succesfully assigned role");
				}
			} catch (OIRSException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(option.equals("Delete"))
		{
			String deleteUserId = request.getParameter("deleteUserId");
			//userbean.setUserId(deleteUserId);
			System.out.println(deleteUserId);
			try {
				String res = adminService.deleteUser(deleteUserId);
				if(res==null){
					out.print("User not found!!! Enter the valid user ID");
				}
				else{
					out.print("Deleted successfully");
				}
			} catch (OIRSException e) {
				System.out.println(e.getMessage());
			}
		}
		else if(option.equals("RaiseRequisitions"))
		{
			HttpSession session = request.getSession(false);
			if(session!=null){

				RequisitionBean reqBean = new RequisitionBean();
				String rmId = userbean.getUserId();//(String) session.getAttribute("userId"); //
				System.out.println("RaiseReq -->"+rmId);
				IRMService rmService = new RMServiceImpl();
				reqBean.setReqRmId(rmId);
				try {
					List<ProjectBean> prjBeanList = rmService.getProjectDetailsByStatus(rmId, "OPEN");
					for(ProjectBean prj : prjBeanList)
					{
						System.out.println(prj.getProjectId()+"\t"+prj.getProjectName());
					}
					request.setAttribute("projectDetails", prjBeanList);
					getServletContext().getRequestDispatcher("/RaiseRequisition.jsp").forward(request,response);
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}


		}
		else if(option.equals("Raise Requisition")){
			HttpSession session = request.getSession(false);
			if(session!=null){
				RequisitionBean reqBean = new RequisitionBean();
				IRMService rmService = new RMServiceImpl();
				//			ProjectBean projBean = new ProjectBean();
				try {
					System.out.println("session uid : "+session.getAttribute("userId"));
					String rmId = userbean.getUserId();
					System.out.println(rmId);
					String projId = request.getParameter("projId");
					String skill = request.getParameter("skill").toLowerCase();
					String domain = request.getParameter("domain").toLowerCase();
					int numReq = Integer.parseInt(request.getParameter("number"));
					String vacancy = request.getParameter("vacancy");
					reqBean.setReqRmId(rmId);
					reqBean.setReqProjectId(projId);
					reqBean.setReqSkill(skill);
					reqBean.setReqDomain(domain);
					reqBean.setReqVacancyName(vacancy);
					reqBean.setReqNoReq(numReq);


					String reqId = rmService.raiseRequisition(reqBean);
					if(reqId==null)
					{
						out.println("Raise Requisition failed");
					}
					else{

						out.println("Requisition Raised Succesfully for Requisition Id "+reqId);

					}
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
					response.setStatus(500);
					//response.sendError(response.SC_UNAUTHORIZED, "Invalid User name/Password");
					request.setAttribute("error","Oops!!! something went wrong : "+e.getMessage());
					getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request,response);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					response.setStatus(500);
					//response.sendError(response.SC_UNAUTHORIZED, "Invalid User name/Password");
					request.setAttribute("error","Oops!!! something went wrong : "+e.getMessage());
					getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request,response);
				}

			}

		}
		else if(option.equals("Accept/Reject")){

			HttpSession session = request.getSession(false);
			if(session!=null){

				System.out.println("Accept or Reject : "+session.getAttribute("userId"));

				String rmId = userbean.getUserId();//(String) session.getAttribute("userId");
				try {
					List<RequisitionBean> reqDetails = iRMGEService.getRequisitionByRMId(rmId);
					session.setAttribute("reqDetails", reqDetails);
					getServletContext().getRequestDispatcher("/RMReqAcceptReject.jsp").forward(request,response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(option.equals("View Requisition Response")){
			HttpSession session = request.getSession(false);
			if(session!=null){
				String reqId = request.getParameter("reqId");	
				session.setAttribute("reqId", reqId);
				IRMService rmService = new RMServiceImpl();
				try {
					RequisitionBean reqBean = iRMGEService.getRequisitionDetails(reqId);
					session.setAttribute("numReq", reqBean.getReqNoReq());
					session.setAttribute("numAssigned", reqBean.getNumAssigned());
					List<EmployeeBean> empList = rmService.viewRequestRes(reqId);
					request.setAttribute("selectStatus", true);
					session.setAttribute("empDetails", empList);
					getServletContext().getRequestDispatcher("/ViewRequisitionResponse.jsp").forward(request,response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

			}
		
		}

		else if(option.equals("Accept Employee")){
			HttpSession session = request.getSession(false);
			if(session!=null){
				String empIds[] = request.getParameterValues("empIds");
				String reqId = (String) session.getAttribute("reqId");
				boolean result = false;
				IRMService rmService = new RMServiceImpl();
				if(empIds.length>0){
					try {
						for(String empId:empIds){
							System.out.println("Selected emp : "+empId);
							result = rmService.acceptRes(empId, reqId);
						}	
						request.setAttribute("selectStatus", true);
						List<EmployeeBean> empList;
						empList = rmService.viewRequestRes(reqId);
						session.setAttribute("empDetails", empList);
						session.setAttribute("result", result);
						out.print("Employee accepted successfully!!!");	
						
						//go back with reload
						
						//getServletContext().getRequestDispatcher("/ViewRequisitionResponse.jsp").forward(request,response);
					} catch (OIRSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					request.setAttribute("selectStatus", false);
					getServletContext().getRequestDispatcher("/ViewRequisitionResponse.jsp").forward(request,response);				
				}

			}
	

		}

		else if(option.equals("Reject Employee")){
			HttpSession session = request.getSession(false);
			if(session!=null){

				String empIds[] = request.getParameterValues("empIds");
				String reqId = (String) session.getAttribute("reqId");
				boolean result = false;
				IRMService rmService = new RMServiceImpl();
				if(empIds.length>0){
					try {
						for(String empId:empIds){
							System.out.println("Selected emp : "+empId);
							result = rmService.rejectRes(empId, reqId);
						}	
						request.setAttribute("selectStatus", true);
						List<EmployeeBean> empList;

						empList = rmService.viewRequestRes(reqId);
						session.setAttribute("empDetails", empList);
						session.setAttribute("result", result);
						out.print("Employee rejected successfully!!!");
						//getServletContext().getRequestDispatcher("/ViewRequisitionResponse.jsp").forward(request,response);
					} catch (OIRSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					request.setAttribute("selectStatus", false);
					getServletContext().getRequestDispatcher("/ViewRequisitionResponse.jsp").forward(request,response);				
				}
			}


		}


		else if(option.equals("Close/unassign")){
			HttpSession session = request.getSession(false);
			if(session!=null){
				RequisitionBean reqBean = new RequisitionBean();
				String rmId = (String) session.getAttribute("userId"); // userbean.getUserId();
				IRMService rmService = new RMServiceImpl();
				reqBean.setReqRmId(rmId);
				try {
					List<ProjectBean> prjBeanList = rmService.getProjectDetailsByStatus(rmId, "OPEN");
					for(ProjectBean prj : prjBeanList)
					{
						System.out.println(prj.getProjectId()+"\t"+prj.getProjectName());
					}
					request.setAttribute("projectDetails", prjBeanList);
					getServletContext().getRequestDispatcher("/closeUnasignProj.jsp").forward(request,response);
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}

			}
	
		}

		else if(option.equals("Close Project"))
		{
			HttpSession session = request.getSession(false);
			if(session!=null){

				String projId = request.getParameter("projId");
				IRMService rmService = new RMServiceImpl();
				try {
					boolean result = rmService.unassignByPrjId(projId);
					if(result == true){
						out.println("Project Closed Succesfully");
					}
					else{
						out.println("Failed Closing the project");
					}
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}
	
		}
		else if(option.equals("Unassign Employee"))
		{

			HttpSession session = request.getSession(false);
			if(session!=null){
				IRMService rmService = new RMServiceImpl();
				String prjId = request.getParameter("projId");
				try {
					List<EmployeeBean> bean = rmService.getEmpDetailsByPrjId(prjId);
					request.setAttribute("empBean", bean);
					request.setAttribute("prjId", prjId);
					getServletContext().getRequestDispatcher("/RMUnassignByEmpID.jsp").forward(request, response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


		}
		else if(option.equals("unassignEmployee")){

			HttpSession session = request.getSession(false);
			if(session!=null){
				IRMService rmService = new RMServiceImpl();
				String empId = request.getParameter("empId");
				try {
					boolean result = rmService.unassignProject(empId);
					String prjId =  (String) request.getAttribute("prjId");
					List<EmployeeBean> bean = rmService.getEmpDetailsByPrjId(prjId);
					request.setAttribute("empBean", bean);
					getServletContext().getRequestDispatcher("/RMUnassignByEmpID.jsp").forward(request, response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	
		}

		else if(option.equals("ViewAllRequisitions"))
		{

			HttpSession session = request.getSession(false);
			if(session!=null){

				List<RequisitionBean> reqBeanList;
				try {
					reqBeanList = iRMGEService.getAllRequisitions();
					for(RequisitionBean req : reqBeanList)
					{
						System.out.println(req.getReqId()+"\t"+req.getReqRmId());
					}
					session.setAttribute("reqDetails", reqBeanList);
					getServletContext().getRequestDispatcher("/allRequisitions.jsp").forward(request,response);
				} catch (OIRSException e) {
					System.out.println(e.getMessage());
				}
			}

		}
		else if(option.equals("View Requisition details"))
		{

			HttpSession session = request.getSession(false);
			if(session!=null){
				String reqId = request.getParameter("reqId");
				session.setAttribute("reqId", reqId);
				try {
					RequisitionBean reqBean = iRMGEService.getRequisitionDetails(reqId);
					request.setAttribute("reqBean", reqBean);
					session.setAttribute("reqBean", reqBean);
					getServletContext().getRequestDispatcher("/ViewRequisitionDetails.jsp").forward(request,response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	

		}
		else if(option.equals("Search Employee"))
		{

			HttpSession session = request.getSession(false);
			if(session!=null){

				RequisitionBean reqBean = (RequisitionBean) session.getAttribute("reqBean");

				//System.out.println(request.getAttribute("reqBean"));
				System.out.println(reqBean.getReqId()+ "-------Search");
				System.out.println(reqBean.getReqSkill()+ "-------Search");
				System.out.println(session.getAttribute("reqId")+"-----Session reqId");

				try {
					List<EmployeeBean> empList = iRMGEService.searchEmployee(reqBean);
					//request.setAttribute("reqId", reqBean.getReqId());
					session.setAttribute("numReq", reqBean.getReqNoReq());
					session.setAttribute("numAssigned", reqBean.getNumAssigned());
					request.setAttribute("empList", empList);
					getServletContext().getRequestDispatcher("/searchEmployee.jsp").forward(request,response);

				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			

		}
		else if(option.equals("View Employee Details")){

			HttpSession session = request.getSession(false);
			if(session!=null){
				String empId = request.getParameter("empId");
				String reqId = (String) session.getAttribute("reqId");

				System.out.println("Req id -->"+reqId);
				EmployeeBean empBean;
				try {
					empBean = iRMGEService.getEmployeeDetails(empId);
					request.setAttribute("empBean", empBean);
					//request.setAttribute("reqId", reqId);
					getServletContext().getRequestDispatcher("/DisplayEmployeeDetails.jsp").forward(request,response);
				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


		}

		else if(option.equals("Assign Employee")){
			HttpSession session = request.getSession(false);
			if(session!=null){
				String reqId = (String) session.getAttribute("reqId");
				System.out.println("req Id -->"+reqId);
				String empId = (String) session.getAttribute("empId");
				System.out.println("Assign req -->"+reqId);
				System.out.println("Assign emp -->"+empId);
				try {
					boolean result = iRMGEService.assignProject(empId, reqId);

					if(result){
						System.out.println(result +"Assigned!!");
						//int numAssigned = (int) session.getAttribute("numAssigned");
						//session.setAttribute("numAssigned", ++numAssigned);
						try {
							RequisitionBean reqBean = (RequisitionBean) session.getAttribute("reqBean");
							int numAssigned = reqBean.getNumAssigned()+1;
							reqBean = iRMGEService.updateReqNumberAssigned(reqId, numAssigned);
							session.setAttribute("reqBean", reqBean);
							List<EmployeeBean> empList = iRMGEService.searchEmployee(reqBean);
							//request.setAttribute("reqId", reqBean.getReqId());
							session.setAttribute("numReq", reqBean.getReqNoReq());
							session.setAttribute("numAssigned", reqBean.getNumAssigned());
							request.setAttribute("empList", empList);
							getServletContext().getRequestDispatcher("/searchEmployee.jsp").forward(request,response);

						} catch (OIRSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					else
					{
						out.print("Failed to assign");
					}


				} catch (OIRSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}




			//searchEmployee(reqBean);
		}

	}

}
