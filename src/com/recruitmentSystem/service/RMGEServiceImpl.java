package com.recruitmentSystem.service;

import java.util.List;

import oracle.net.aso.i;

import com.recruitmentSystem.bean.EmployeeBean;
import com.recruitmentSystem.bean.RequisitionBean;
import com.recruitmentSystem.dao.IRMGEDAO;
import com.recruitmentSystem.dao.RMGEDAOImpl;
import com.recruitmentSystem.exception.OIRSException;

public class RMGEServiceImpl implements IRMGEService {

	IRMGEDAO iRMGEDAO = new RMGEDAOImpl();
	
	
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
		List<EmployeeBean> list = iRMGEDAO.searchEmployee(reqBean);
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
		List<String> list = iRMGEDAO.getRmIds();
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
		List<RequisitionBean> list = iRMGEDAO.getAllRequisitions();
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

		RequisitionBean bean = iRMGEDAO.getRequisitionDetails(reqId);
		return bean;
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
		EmployeeBean bean = iRMGEDAO.getEmployeeDetails(empId);
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
		List<RequisitionBean> list = iRMGEDAO.getRequisitionByStatus(status);
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
		List<RequisitionBean> list = iRMGEDAO.getRequisitionByStatusRM(rmId, status);
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
		List<RequisitionBean> list = iRMGEDAO.getRequisitionByRMId(rmId);
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
		boolean result = iRMGEDAO.assignProject(empId, reqId);
		return result;
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
	public RequisitionBean updateReqNumberAssigned(String reqId, int numAssigned)
			throws OIRSException {
		// TODO Auto-generated method stub
		RequisitionBean bean = iRMGEDAO.updateReqNumberAssigned(reqId, numAssigned);
		return bean;
	}

}
