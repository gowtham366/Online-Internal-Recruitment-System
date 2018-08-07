package com.recruitmentSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.recruitmentSystem.bean.EmployeeBean;
import com.recruitmentSystem.bean.RequisitionBean;
import com.recruitmentSystem.exception.OIRSException;
import com.recruitmentSystem.util.DBConnection;

public class RMGEDAOImpl implements IRMGEDAO {
	Connection connection = null;
	
	/*******************************************************************************************************
	 - Function Name	:	searchEmployee()
	 - Input Parameters	:	reqBean
	 - Return Type		:	List<EmployeeBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Search Employee for Requisition
	 ********************************************************************************************************/
	@Override
	public List<EmployeeBean> searchEmployee(RequisitionBean reqBean)
			throws OIRSException {
		// TODO Auto-generated method stub
		List<EmployeeBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		String skill = reqBean.getReqSkill().toLowerCase();
		String domain = reqBean.getReqDomain().toLowerCase();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_RMGE_SEARCH_EMPLOYEE);
			preparedStatement.setString(1, skill);
			preparedStatement.setString(2, domain);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				EmployeeBean bean = new EmployeeBean();
				//employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id
				bean.setEmpId(resultSet.getString(1));
				bean.setEmpName(resultSet.getString(2));
				bean.setEmpSkill(resultSet.getString(3));
				bean.setEmpDomain(resultSet.getString(4));
				bean.setEmpExp(resultSet.getInt(5));
				bean.setEmpProjectId(resultSet.getString(6));
				list.add(bean);
				System.out.println(resultSet.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	
	/*******************************************************************************************************
	 - Function Name	:	getRmIds()
	 - Input Parameters	:	
	 - Return Type		:	List<String>
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Get rmId's
	 ********************************************************************************************************/
	@Override
	public List<String> getRmIds() throws OIRSException {
		// TODO Auto-generated method stub
		connection = DBConnection.getConnection();
		List<String> list = new ArrayList<>();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_GET_RM_IDS);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				System.out.println(resultSet.getString(1));
				list.add(resultSet.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	
	/*******************************************************************************************************
	 - Function Name	:	getAllRequisitions()
	 - Input Parameters	:	
	 - Return Type		:	List<RequisitionBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Get all the Requisitions
	 ********************************************************************************************************/
	@Override
	public List<RequisitionBean> getAllRequisitions() throws OIRSException {
		// TODO Auto-generated method stub
		List<RequisitionBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_GET_ALL_REQ_OPEN);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				//requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required
				RequisitionBean bean = new RequisitionBean();
				System.out.println(resultSet.getString(1));
				bean.setReqId(resultSet.getString(1));
				bean.setReqRmId(resultSet.getString(2));
				bean.setReqProjectId(resultSet.getString(3));
				bean.setReqDateCreated(resultSet.getString(4));
				bean.setReqDateClosed(resultSet.getString(5));
				bean.setReqStatus(resultSet.getString(6));
				bean.setReqVacancyName(resultSet.getString(7));
				bean.setReqSkill(resultSet.getString(8));
				bean.setReqDomain(resultSet.getString(9));
				bean.setReqNoReq(resultSet.getInt(10));
				bean.setReqProjectName(resultSet.getString(11));
				bean.setNumAssigned(resultSet.getInt(12));
				list.add(bean);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		return list;
	}

	
	/*******************************************************************************************************
	 - Function Name	:	getRequisitionDetails()
	 - Input Parameters	:	reqId
	 - Return Type		:	RequisitionBean
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting requisition details based on reqId
	 ********************************************************************************************************/
	@Override
	public RequisitionBean getRequisitionDetails(String reqId)
			throws OIRSException {
		// TODO Auto-generated method stub
		
		//List<RequisitionBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		RequisitionBean bean = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_GET_REQ_BY_REQID);
			preparedStatement.setString(1, reqId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				bean = new RequisitionBean();
				//requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required	
				System.out.println(resultSet.getString(1));
				bean.setReqId(resultSet.getString(1));
				bean.setReqRmId(resultSet.getString(2));
				bean.setReqProjectId(resultSet.getString(3));
				bean.setReqDateCreated(resultSet.getString(4));
				bean.setReqDateClosed(resultSet.getString(5));
				bean.setReqStatus(resultSet.getString(6));
				bean.setReqVacancyName(resultSet.getString(7));
				bean.setReqSkill(resultSet.getString(8));
				bean.setReqDomain(resultSet.getString(9));
				bean.setReqNoReq(resultSet.getInt(10));
				bean.setNumAssigned(resultSet.getInt(11));
				//list.add(bean);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		return bean;
	}

	
	/*******************************************************************************************************
	 - Function Name	:	getRequisitionByStatus()
	 - Input Parameters	:	status
	 - Return Type		:	List<RequisitionBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting requisitions based on status
	 ********************************************************************************************************/
	@Override
	public List<RequisitionBean> getRequisitionByStatus(String status)
			throws OIRSException {
		// TODO Auto-generated method stub
		List<RequisitionBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VIEW_REQUISITION_BY_STATUS);
			preparedStatement.setString(1, status);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				//requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required
				RequisitionBean bean = new RequisitionBean();
				System.out.println(resultSet.getString(1));
				bean.setReqId(resultSet.getString(1));
				bean.setReqRmId(resultSet.getString(2));
				bean.setReqProjectId(resultSet.getString(3));
				bean.setReqDateCreated(resultSet.getString(4));
				bean.setReqDateClosed(resultSet.getString(5));
				bean.setReqStatus(resultSet.getString(6));
				bean.setReqVacancyName(resultSet.getString(7));
				bean.setReqSkill(resultSet.getString(8));
				bean.setReqDomain(resultSet.getString(9));
				bean.setReqNoReq(resultSet.getInt(10));
				list.add(bean);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		return list;
	}

	
	/*******************************************************************************************************
	 - Function Name	:	getRequisitionByStatusRM()
	 - Input Parameters	:	rmId, status
	 - Return Type		:	List<RequisitionBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting requisition using status and rmId
	 ********************************************************************************************************/
	@Override
	public List<RequisitionBean> getRequisitionByStatusRM(String rmId,
			String status) throws OIRSException {
		// TODO Auto-generated method stub
		List<RequisitionBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VIEW_REQUISITION_BY_STATUS);
			preparedStatement.setString(1, rmId);
			preparedStatement.setString(2, status);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				//requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required
				RequisitionBean bean = new RequisitionBean();
				System.out.println(resultSet.getString(1));
				bean.setReqId(resultSet.getString(1));
				bean.setReqRmId(resultSet.getString(2));
				bean.setReqProjectId(resultSet.getString(3));
				bean.setReqDateCreated(resultSet.getString(4));
				bean.setReqDateClosed(resultSet.getString(5));
				bean.setReqStatus(resultSet.getString(6));
				bean.setReqVacancyName(resultSet.getString(7));
				bean.setReqSkill(resultSet.getString(8));
				bean.setReqDomain(resultSet.getString(9));
				bean.setReqNoReq(resultSet.getInt(10));
				list.add(bean);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		return list;
	}

	
	
	/*******************************************************************************************************
	 - Function Name	:	getRequisitionByRMId()
	 - Input Parameters	:	rmId
	 - Return Type		:	List<RequisitionBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting requisitions using rmId
	 ********************************************************************************************************/
	@Override
	public List<RequisitionBean> getRequisitionByRMId(String rmId)
			throws OIRSException {
		// TODO Auto-generated method stub
		List<RequisitionBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_GET_REQ_RMID);
			preparedStatement.setString(1, rmId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				//requisition_id,rm_id,project_id,date_created,date_closed,current_status,vacancy_name,skill,domain,number_required,project_name
				RequisitionBean bean = new RequisitionBean();
				System.out.println(resultSet.getString(1));
				bean.setReqId(resultSet.getString(1));
				bean.setReqRmId(resultSet.getString(2));
				bean.setReqProjectId(resultSet.getString(3));
				bean.setReqDateCreated(resultSet.getString(4));
				bean.setReqDateClosed(resultSet.getString(5));
				bean.setReqStatus(resultSet.getString(6));
				bean.setReqVacancyName(resultSet.getString(7));
				bean.setReqSkill(resultSet.getString(8));
				bean.setReqDomain(resultSet.getString(9));
				bean.setReqNoReq(resultSet.getInt(10));
				bean.setReqProjectName(resultSet.getString(11));
				bean.setNumAssigned(resultSet.getInt(12));
				list.add(bean);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Sql exe : "+e.getMessage());
			e.printStackTrace();
		}
		
 		return list;
	}
	
	
	
	/*******************************************************************************************************
	 - Function Name	:	assignProject()
	 - Input Parameters	:	empId, reqId
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Assign project for the Employee
	 ********************************************************************************************************/
	@Override
	public boolean assignProject(String empId, String reqId)
			throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = false;
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_ASSIGN_PROJECT);
			preparedStatement.setString(1, reqId);
			preparedStatement.setString(2, empId);
			int queryResult = preparedStatement.executeUpdate();
			if(queryResult!=0){
				result = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public void generateReport() throws OIRSException {
		// TODO Auto-generated method stub

	}
	
	
	/*******************************************************************************************************
	 - Function Name	:	getEmployeeDetails()
	 - Input Parameters	:	empId
	 - Return Type		:	EmployeeBean
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Get Particular Employee Details
	 ********************************************************************************************************/
	@Override
	public EmployeeBean getEmployeeDetails(String empId) throws OIRSException {
		// TODO Auto-generated method stub
		
		EmployeeBean bean = new EmployeeBean();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_GET_EMPLOYEE_DETAILS_BY_EMPID);
			preparedStatement.setString(1, empId);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				//employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id
				bean.setEmpId(resultSet.getString(1));
				bean.setEmpName(resultSet.getString(2));
				bean.setEmpSkill(resultSet.getString(3));
				bean.setEmpDomain(resultSet.getString(4));
				bean.setEmpExp(resultSet.getInt(5));
				bean.setEmpProjectId(resultSet.getString(6));
				
				System.out.println(resultSet.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bean;
	}

	
	
	/*******************************************************************************************************
	 - Function Name	:	updateReqNumberAssigned()
	 - Input Parameters	:	reqId, numAssigned
	 - Return Type		:	RequisitionBean
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Update the Requisition Number
	 ********************************************************************************************************/
	@Override
	public RequisitionBean updateReqNumberAssigned(String reqId, int numAssigned) throws OIRSException {
		// TODO Auto-generated method stub
		RequisitionBean bean = null;
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_GET_NUMBER_ASSIGNED);
			preparedStatement.setInt(1, numAssigned);
			preparedStatement.setString(2, reqId);
			int queryResult = preparedStatement.executeUpdate();
			if(queryResult!=0){
				bean =getRequisitionDetails(reqId); 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bean;
	}

}
