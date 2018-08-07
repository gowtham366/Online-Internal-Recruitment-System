package com.recruitmentSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.recruitmentSystem.bean.EmployeeBean;
import com.recruitmentSystem.bean.ProjectBean;
import com.recruitmentSystem.bean.RequisitionBean;
import com.recruitmentSystem.exception.OIRSException;
import com.recruitmentSystem.util.DBConnection;

public class RMDAOImpl implements IRMDAO {

	Connection connection = null;



	/*******************************************************************************************************
	 - Function Name	:	raiseRequisition()
	 - Input Parameters	:	reqBean
	 - Return Type		:	String
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Raising requisition
	 ********************************************************************************************************/
	@Override
	public String raiseRequisition(RequisitionBean reqBean)
			throws OIRSException {
		// TODO Auto-generated method stub
		connection = DBConnection.getConnection();
		String result =null;
		String reqId = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(IQueryMapper.QUERY_COUNT_REQ);
			if(resultSet.next()){
				//requisition_id,rm_id,project_id,date_created,current_status,vacancy_name,skill,domain,number_required
				//(?,?,?,SYSDATE,'OPEN',?,?,?,?)
				reqId = "RQ"+String.valueOf(resultSet.getInt(1)+1);
				System.out.println("Req Id generated: "+reqId);
				preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_RAISE_REQ);
				preparedStatement.setString(1, reqId);
				preparedStatement.setString(2, reqBean.getReqRmId());
				preparedStatement.setString(3, reqBean.getReqProjectId());
				preparedStatement.setString(4, reqBean.getReqVacancyName());
				preparedStatement.setString(5, reqBean.getReqSkill());
				preparedStatement.setString(6, reqBean.getReqDomain());
				preparedStatement.setInt(7, reqBean.getReqNoReq());
				resultSet=preparedStatement.executeQuery();			
				if(resultSet.next()){
					result = reqId;
					System.out.println(result);
				}
				else{
					throw new OIRSException("Failed to raise requisition..Try again");
				}

			}
			else
			{
				throw new OIRSException("Failed to generate Requisition ID..Try again");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new OIRSException("RMDAO SQL Exception : "+e.getMessage());
		}
		return result;
	}



	/*******************************************************************************************************
	 - Function Name	:	viewRequestRes()
	 - Input Parameters	:	reqId
	 - Return Type		:	List<EmployeeBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	View requisitions using reqId
	 ********************************************************************************************************/
	@Override
	public List<EmployeeBean> viewRequestRes(String reqId) throws OIRSException {
		// TODO Auto-generated method stub

		List<EmployeeBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VIEW_REQ_RES);
			preparedStatement.setString(1, reqId);
			resultSet=preparedStatement.executeQuery();	
			while(resultSet.next()){
				EmployeeBean bean = new EmployeeBean();
				//employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id
				bean.setEmpId(resultSet.getString("employee_id"));
				bean.setEmpName(resultSet.getString("employee_name"));
				bean.setEmpSkill(resultSet.getString("skill"));
				bean.setEmpDomain(resultSet.getString("domain"));
				bean.setEmpExp(resultSet.getInt("experience_yrs"));
				bean.setEmpProjectId(resultSet.getString("project_id"));
				bean.setEmpReqId(resultSet.getString("req_id"));
				list.add(bean);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}



	/*******************************************************************************************************
	 - Function Name	:	getReqIds()
	 - Input Parameters	:	rmId
	 - Return Type		:	List<String>
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Get requisition Id's based on rmId
	 ********************************************************************************************************/
	@Override
	public List<String> getReqIds(String rmId) throws OIRSException {
		// TODO Auto-generated method stub
		connection = DBConnection.getConnection();
		List<String> list = new ArrayList<>();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VIEW_ALL_REQUISITION);
			preparedStatement.setString(1, rmId);
			while(resultSet.next()){
				System.out.println(resultSet.getString("requisition_id"));
				list.add(resultSet.getString("requisition_id"));				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	/*******************************************************************************************************
	 - Function Name	:	getProjectDetails()
	 - Input Parameters	:	rmId
	 - Return Type		:	List<ProjectBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting Project Details
	 ********************************************************************************************************/
	@Override
	public List<ProjectBean> getProjectDetails(String rmId)
			throws OIRSException {
		// TODO Auto-generated method stub

		List<ProjectBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VIEW_PROJECTS);
			preparedStatement.setString(1, rmId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				ProjectBean bean = new ProjectBean();
				bean.setProjectId(resultSet.getString("project_id"));
				bean.setProjectRmId(resultSet.getString("rm_id"));
				bean.setProjectName(resultSet.getString("project_name"));
				bean.setProjectDesc(resultSet.getString("description"));
				bean.setProjectStartDate(resultSet.getString("start_date"));
				bean.setProjectEndDate(resultSet.getString("end_date"));
				bean.setProjectStatus(resultSet.getString("status"));
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
	 - Input Parameters	:	rmId
	 - Return Type		:	List<RequisitionBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Get Requisition Details
	 ********************************************************************************************************/
	@Override
	public List<RequisitionBean> getRequisitionDetails(String rmId)
			throws OIRSException {
		// TODO Auto-generated method stub

		List<RequisitionBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VIEW_ALL_REQUISITION);
			preparedStatement.setString(1, rmId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				RequisitionBean bean = new RequisitionBean();
				//requisition_id,rm_id,project_id,date_created,date_closed,current_status,vaccancy_name,skill,domain,number_required
				bean.setReqId(resultSet.getString("requisition_id"));
				bean.setReqRmId(resultSet.getString("rm_id"));
				bean.setReqProjectId(resultSet.getString("project_id"));
				bean.setReqDateCreated(resultSet.getString("date_created"));
				bean.setReqDateClosed(resultSet.getString("date_closed"));
				bean.setReqStatus(resultSet.getString("current_Status"));
				bean.setReqVacancyName(resultSet.getString("vacancy_name"));
				bean.setReqSkill(resultSet.getString("skill"));
				bean.setReqDomain(resultSet.getString("domain"));
				bean.setReqNoReq(resultSet.getInt("number_required"));
				bean.setNumAssigned(resultSet.getInt("number_assigned"));
				list.add(bean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}



	/*******************************************************************************************************
	 - Function Name	:	getRequisitionDetailsByStatus()
	 - Input Parameters	:	rmId, status
	 - Return Type		:	List<RequisitionBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting requisition details based on status
	 ********************************************************************************************************/
	@Override
	public List<RequisitionBean> getRequisitionDetailsByStatus(String rmId,
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
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				RequisitionBean bean = new RequisitionBean();
				//requisition_id,rm_id,project_id,date_created,date_closed,current_status,vaccancy_name,skill,domain,number_required
				bean.setReqId(resultSet.getString("requisition_id"));
				bean.setReqRmId(resultSet.getString("rm_id"));
				bean.setReqProjectId(resultSet.getString("project_id"));
				bean.setReqDateCreated(resultSet.getString("date_created"));
				bean.setReqDateClosed(resultSet.getString("date_closed"));
				bean.setReqStatus(resultSet.getString("current_Status"));
				bean.setReqVacancyName(resultSet.getString("skill"));
				bean.setReqDomain(resultSet.getString("domain"));
				bean.setReqNoReq(resultSet.getInt("number_required"));	
				bean.setNumAssigned(resultSet.getInt("number_assigned"));
				list.add(bean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}



	/*******************************************************************************************************
	 - Function Name	:	closeProject()
	 - Input Parameters	:	prjId
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	return String
	 ********************************************************************************************************/
	@Override
	public boolean closeProject(String prjId) throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = false;
		//QUERY_CLOSE_REQ
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_CLOSE_REQ_BY_PRJID);
			preparedStatement.setString(1, prjId);
			int queryResult = preparedStatement.executeUpdate();
			if(queryResult!=0){
				preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_CLOSE_PROJECT);
				preparedStatement.setString(1, prjId);
				int queryResult2 = preparedStatement.executeUpdate();
				if(queryResult2!=0){
					result = true;	
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}



	/*******************************************************************************************************
	 - Function Name	:	updateEmpPrjId()
	 - Input Parameters	:	empId, prjId
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Update Employee By Project Id
	 ********************************************************************************************************/
	@Override
	public boolean updateEmpPrjId(String empId, String prjId)
			throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = false;
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_UPDATE_RES);
			preparedStatement.setString(1, prjId);
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


	/*******************************************************************************************************
	 - Function Name	:	acceptRes()
	 - Input Parameters	:	empId, reqId
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM
	 - Creation Date	:	07/07/2018
	 - Description		:	return boolean
	 ********************************************************************************************************/
	@Override
	public boolean acceptRes(String empId,String reqId) throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = false;
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		int vacCount = 0;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VACCANCY_COUNT_PRJID);
			preparedStatement.setString(1, reqId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				vacCount = resultSet.getInt(1);
				String prjId = resultSet.getString(2);
				System.out.println("vac count : "+vacCount);
				System.out.println("prjId : "+prjId);
				if(vacCount > 0){
					boolean result1 = updateEmpPrjId(empId,prjId);
					if(result1){
						--vacCount;
						System.out.println("vac count after update : "+vacCount);
						preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_DEC_NUMBER_REQ);
						preparedStatement.setInt(1,vacCount);
						preparedStatement.setString(2, reqId);
						int queryResult = preparedStatement.executeUpdate();
						if(queryResult!=0){
							if(vacCount == 0){
								preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_CLOSE_REQ_BY_REQID);
								preparedStatement.setString(1, reqId);
								int queryResult2 = preparedStatement.executeUpdate();
								if(queryResult2!=0){
									System.out.println("Requisition closed!!!");
								}
							}
							result = true;
						}

					}
				}
				else{
					throw new OIRSException("No vacancies are available");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}



	/*******************************************************************************************************
	 - Function Name	:	rejectRes()
	 - Input Parameters	:	empId, reqId
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM
	 - Creation Date	:	07/07/2018
	 - Description		:	return boolean
	 ********************************************************************************************************/
	@Override
	public boolean rejectRes(String empId,String reqId) throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = false;
		result = updateEmpPrjId(empId,"rmg");
		return result;
	}



	/*******************************************************************************************************
	 - Function Name	:	unassignProject()
	 - Input Parameters	:	empId
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Unassigning the project
	 ********************************************************************************************************/
	@Override
	public boolean unassignProject(String empId) throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = false;
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_UNASSIGN_PROJECT);
			preparedStatement.setString(1, empId);
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



	/*******************************************************************************************************
	 - Function Name	:	unassignByPrjId()
	 - Input Parameters	:	prjId
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Unassigning Project By Project Id
	 ********************************************************************************************************/
	@Override
	public boolean unassignByPrjId(String prjId) throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = false;
		int queryResult = 0;
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_UNASSIGN_PROJECT_BY_PRJID);
			preparedStatement.setString(1, prjId);
			queryResult = preparedStatement.executeUpdate();
			if(queryResult >= 0){
				System.out.println("emp un assigned");
				preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_CLOSE_PROJECT);	
				preparedStatement.setString(1, prjId);
				queryResult = preparedStatement.executeUpdate();
				if(queryResult != 0){
					result = true;	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void generateReport() throws OIRSException {
		// TODO Auto-generated method stub

	}



	/*******************************************************************************************************
	 - Function Name	:	getProjectDetailsByStatus()
	 - Input Parameters	:	rmId, status
	 - Return Type		:	List<ProjectBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting Employee Details By Project Status
	 ********************************************************************************************************/
	@Override
	public List<ProjectBean> getProjectDetailsByStatus(String rmId,
			String status) throws OIRSException {
		// TODO Auto-generated method stub
		List<ProjectBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_VIEW_PROJECTS_BY_STATUS);
			preparedStatement.setString(1, rmId);
			preparedStatement.setString(2, status);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				ProjectBean bean = new ProjectBean();
				bean.setProjectId(resultSet.getString("project_id"));
				bean.setProjectRmId(resultSet.getString("rm_id"));
				bean.setProjectName(resultSet.getString("project_name"));
				bean.setProjectDesc(resultSet.getString("description"));
				bean.setProjectStartDate(resultSet.getString("start_date"));
				bean.setProjectEndDate(resultSet.getString("end_date"));
				bean.setProjectStatus(resultSet.getString("status"));
				list.add(bean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return list;
	}



	/*******************************************************************************************************
	 - Function Name	:	getEmpDetailsByPrjId()
	 - Input Parameters	:	String prjId
	 - Return Type		:	List<EmployeeBean>
	 - Throws		    :  	OIRSException
	 - Author	     	:	Kamatchi, Anusha
	 - Creation Date	:	07/07/2018
	 - Description		:	Getting Employee Details By Project Id
	 ********************************************************************************************************/
	@Override
	public List<EmployeeBean> getEmpDetailsByPrjId(String prjId)
			throws OIRSException {
		// TODO Auto-generated method stub
		List<EmployeeBean> list = new ArrayList<>();
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_GET_EMP_BY_PRJID);
			preparedStatement.setString(1, prjId);
			resultSet=preparedStatement.executeQuery();	
			while(resultSet.next()){
				EmployeeBean bean = new EmployeeBean();
				//employee_id,employee_name,skill,domain,experience_yrs,project_id,req_id
				bean.setEmpId(resultSet.getString("employee_id"));
				bean.setEmpName(resultSet.getString("employee_name"));
				bean.setEmpSkill(resultSet.getString("skill"));
				bean.setEmpDomain(resultSet.getString("domain"));
				bean.setEmpExp(resultSet.getInt("experience_yrs"));
				bean.setEmpProjectId(resultSet.getString("project_id"));
				bean.setEmpReqId(resultSet.getString("req_id"));
				list.add(bean);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
