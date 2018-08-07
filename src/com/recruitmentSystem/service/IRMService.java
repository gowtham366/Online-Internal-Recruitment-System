package com.recruitmentSystem.service;

import java.util.List;

import com.recruitmentSystem.bean.EmployeeBean;
import com.recruitmentSystem.bean.ProjectBean;
import com.recruitmentSystem.bean.RequisitionBean;
import com.recruitmentSystem.exception.OIRSException;

public interface IRMService {
	public String raiseRequisition(RequisitionBean reqBean) throws OIRSException;
	public List<EmployeeBean> viewRequestRes(String reqId) throws OIRSException;
	public List<EmployeeBean> getEmpDetailsByPrjId(String prjId) throws OIRSException;
	public List<String> getReqIds(String rmId) throws OIRSException;
	public List<ProjectBean> getProjectDetails(String rmId) throws OIRSException; 
	public List<ProjectBean> getProjectDetailsByStatus(String rmId,String status) throws OIRSException; 
	public List<RequisitionBean> getRequisitionDetails(String rmId) throws OIRSException;
	public List<RequisitionBean> getRequisitionDetailsByStatus(String rmId, String status) throws OIRSException;
	public boolean closeProject(String prjId) throws OIRSException;
	public boolean updateEmpPrjId(String empId,String prjId) throws OIRSException;
	public boolean acceptRes(String empId,String reqId) throws OIRSException;
	public boolean rejectRes(String empId,String reqId) throws OIRSException;
	public boolean unassignProject(String empId) throws OIRSException;
	public boolean unassignByPrjId(String prjId) throws OIRSException;
	public void generateReport() throws OIRSException;
}
