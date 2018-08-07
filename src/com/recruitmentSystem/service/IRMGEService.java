package com.recruitmentSystem.service;

import java.util.List;

import com.recruitmentSystem.bean.EmployeeBean;
import com.recruitmentSystem.bean.RequisitionBean;
import com.recruitmentSystem.exception.OIRSException;

public interface IRMGEService {
	public abstract List<EmployeeBean> searchEmployee(RequisitionBean reqBean) throws OIRSException;
	//public abstract boolean assignProjectEmp(String empId, String reqId) throws OIRSException;
	public abstract List<String> getRmIds() throws OIRSException;
	public abstract List<RequisitionBean> getAllRequisitions() throws OIRSException;
	public abstract RequisitionBean getRequisitionDetails(String reqId) throws OIRSException;
	public abstract RequisitionBean updateReqNumberAssigned(String reqId,int numAssigned) throws OIRSException;
	public abstract EmployeeBean getEmployeeDetails(String empId) throws OIRSException;
	public abstract List<RequisitionBean> getRequisitionByStatus(String status) throws OIRSException;
	public abstract List<RequisitionBean> getRequisitionByStatusRM(String rmId,String status) throws OIRSException;
	public abstract List<RequisitionBean> getRequisitionByRMId(String rmId) throws OIRSException;
	public abstract boolean assignProject(String empId,String reqId) throws OIRSException;
}
