package com.recruitmentSystem.service;

import java.util.List;

import com.recruitmentSystem.bean.EmployeeBean;
import com.recruitmentSystem.bean.ProjectBean;
import com.recruitmentSystem.bean.RequisitionBean;
import com.recruitmentSystem.dao.IRMDAO;
import com.recruitmentSystem.dao.RMDAOImpl;
import com.recruitmentSystem.exception.OIRSException;

public class RMServiceImpl implements IRMService {

	IRMDAO iRMDAO = new RMDAOImpl(); 
	
	
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
		String reqId = iRMDAO.raiseRequisition(reqBean); 
		return reqId;
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
		List<EmployeeBean> bean = iRMDAO.viewRequestRes(reqId);
		return bean;
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
		List<String> reqIds = iRMDAO.getReqIds(rmId);
		return reqIds;
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
		List<ProjectBean> bean = iRMDAO.getProjectDetails(rmId);
		return bean;
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
		List<RequisitionBean> bean = iRMDAO.getRequisitionDetails(rmId);
		return bean;
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
		List<RequisitionBean> bean = iRMDAO.getRequisitionDetailsByStatus(rmId, status);
		return bean;
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
		boolean result = iRMDAO.closeProject(prjId);
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
	 ********************************************************************************************************/@Override
	public boolean updateEmpPrjId(String empId, String prjId)
			throws OIRSException {
		// TODO Auto-generated method stub
		boolean result = iRMDAO.updateEmpPrjId(empId, prjId);
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
		boolean result = iRMDAO.acceptRes(empId,reqId);
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
		boolean result = iRMDAO.rejectRes(empId,reqId);
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
		boolean result = iRMDAO.unassignProject(empId);
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
		boolean result = iRMDAO.unassignByPrjId(prjId);
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
		List<ProjectBean> list = iRMDAO.getProjectDetailsByStatus(rmId, status);
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
		List<EmployeeBean> bean = iRMDAO.getEmpDetailsByPrjId(prjId);
		return bean;
	}

}
