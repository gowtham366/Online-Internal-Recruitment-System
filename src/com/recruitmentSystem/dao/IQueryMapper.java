package com.recruitmentSystem.dao;

public interface IQueryMapper {
	
	//gowtham team //AUTHENTICATION
	public static final String QUERY_AUTH_USER ="SELECT user_id,role,last_login FROM tbl_user_master WHERE user_id = ? AND password = ?";
	public static final String QUERY_UPDATE_LAST_LOGIN = "UPDATE tbl_user_master SET last_login = SYSDATE WHERE user_id = ?";
	public static final String QUERY_SEARCH_EMPLOYEE = "SELECT employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id FROM tbl_employee_master WHERE domain = ? AND skill = ? ";
	
	//RMGE
	//public static final String QUERY_
	//public static final String QUERY_
	
	
	//ADMIN
	public static final String VIEWUSER="SELECT user_id FROM tbl_user_master";
	public static final String ADDUSER="INSERT INTO tbl_user_master VALUES(?,?,?,SYSDATE,?)";
	public static final String ASSIGNROLE="UPDATE tbl_user_master SET role = ? WHERE user_id = ?";
	public static final String DELETEUSER="DELETE FROM tbl_user_master WHERE user_id=?";
	public static final String GET_USER_IDS = "SELECT user_id FROM tbl_user_master";
	public static final String GET_USER_DETAILS = "SELECT user_id,role,last_login FROM tbl_user_master";
	
	 //RM
	public static final String QUERY_COUNT_REQ = "SELECT COUNT(*) FROM tbl_requisition";
	public static final String QUERY_GET_EMP_BY_PRJID = "SELECT employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id FROM tbl_employee_master WHERE project_id = ?";
	public static final String QUERY_VIEW_PROJECTS_BY_STATUS ="SELECT project_id,rm_id,project_name,description,start_date,end_date,status FROM tbl_project_master WHERE rm_id = ? AND status = ?";
	public static final String QUERY_VIEW_PROJECTS = "SELECT project_id,rm_id,project_name,description,start_date,end_date,status FROM tbl_project_master WHERE rm_id = ?";
	public static final String QUERY_RAISE_REQ = "INSERT INTO tbl_requisition (requisition_id,rm_id,project_id,date_created,current_status,vacancy_name,skill,domain,number_required) VALUES(?,?,?,SYSDATE,'OPEN',?,?,?,?)";
	public static final String QUERY_VIEW_ALL_REQUISITION = "SELECT requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required,number_assigned FROM tbl_requisition WHERE rm_id = ? ORDER BY date_created DESC";
	public static final String QUERY_VIEW_REQUISITION_BY_STATUS = "SELECT requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required,number_assigned FROM tbl_requisition WHERE rm_id = ? AND current_status = ? ";
	public static final String QUERY_VACCANCY_COUNT_PRJID = "SELECT number_required,project_id FROM tbl_requisition WHERE requisition_id = ?";
	public static final String QUERY_VIEW_REQ_RES = "SELECT employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id FROM tbl_employee_master WHERE req_id = ?";
	public static final String QUERY_UPDATE_RES = "UPDATE tbl_employee_master SET project_id = ?,req_id = null WHERE employee_id = ?";
	public static final String QUERY_DEC_NUMBER_REQ = "UPDATE tbl_requisition SET number_required = ? WHERE requisition_id = ?";
	public static final String QUERY_UNASSIGN_PROJECT = "UPDATE tbl_employee_master SET project_id = 'rmg',req_id = null WHERE  employee_id = ?";
 	public static final String QUERY_UNASSIGN_PROJECT_BY_PRJID = "UPDATE tbl_employee_master SET project_id = 'rmg' WHERE project_id = ?";
 	public static final String QUERY_CLOSE_REQ_BY_PRJID = "UPDATE tbl_requisition SET date_closed = SYSDATE,current_status = 'CLOSED' WHERE project_id = ?";
 	public static final String QUERY_CLOSE_REQ_BY_REQID = "UPDATE tbl_requisition SET date_closed = SYSDATE,current_status = 'CLOSED' WHERE requisition_id = ?"; 
 	public static final String QUERY_CLOSE_PROJECT = "UPDATE tbl_project_master SET end_date = SYSDATE,status = 'CLOSED' WHERE project_id = ?";
	public static final String QUERY_VIEW_EMPLOYEES_BY_PROJECTID = "SELECT employee_id,employee_name,skill,domain,experience_yrs,project_id,rm_id FROM tbl_employee_master WHERE project_id = ?";
	
	
	//RMGE
	public static final String QUERY_GET_RM_IDS = "SELECT DISTINCT rm_id FROM tbl_requisition";
	public static final String QUERY_ASSIGN_PROJECT = "UPDATE tbl_employee_master SET req_id =? WHERE employee_id = ?";
	public static final String QUERY_GET_EMPLOYEE_DETAILS_BY_REQID = "SELECT employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id FROM tbl_employee_master WHERE req_id = ?";
	public static final String QUERY_GET_EMPLOYEE_DETAILS_BY_EMPID = "SELECT employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id FROM tbl_employee_master WHERE employee_id = ?";
	public static final String QUERY_GET_ALL_REQ = "SELECT requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required FROM tbl_requisition";
	public static final String QUERY_GET_ALL_REQ_OPEN = "SELECT r.requisition_id,r.rm_id,r.project_id,r.date_created,r.date_closed,r.current_status,r.vacancy_name,r.skill,r.domain,r.number_required,p.project_name,r.number_assigned FROM tbl_requisition r,tbl_project_master p WHERE r.current_status = 'OPEN' AND r.number_required>0 AND p.project_id = r.project_id ORDER BY r.date_created DESC ";
	public static final String QUERY_GET_REQ_RMID = "SELECT r.requisition_id,r.rm_id,r.project_id,r.date_created,r.date_closed,r.current_status,r.vacancy_name,r.skill,r.domain,r.number_required,p.project_name,r.number_assigned FROM tbl_requisition r,tbl_project_master p WHERE r.rm_id = ? AND r.number_required>0 AND p.project_id = r.project_id ORDER BY r.date_created DESC ";
	public static final String QUERY_GET_REQ_BY_REQID = "SELECT requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required,number_assigned FROM tbl_requisition WHERE requisition_id = ?";
	public static final String QUERY_RMGE_SEARCH_EMPLOYEE = "SELECT employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id FROM tbl_employee_master WHERE skill = ? AND domain = ? AND project_id = 'rmg' AND req_id IS NULL";
	public static final String QUERY_GET_NUMBER_ASSIGNED = "UPDATE tbl_requisition SET number_assigned = ? WHERE requisition_id=?";
		
}
