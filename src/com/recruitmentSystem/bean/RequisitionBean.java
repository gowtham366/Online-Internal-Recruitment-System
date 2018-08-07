package com.recruitmentSystem.bean;

import java.util.Date;

public class RequisitionBean {

	private String reqId;
	private String reqRmId;
	private String reqProjectId;
	private String reqDateCreated;
	private String reqDateClosed;
	private String reqStatus;
	private String reqVacancyName;
	private String reqSkill;
	private String reqDomain;
	private String reqProjectName;
	private int reqNoReq;
	private int numAssigned;
	
	public String getReqProjectName() {
		return reqProjectName;
	}
	public void setReqProjectName(String reqProjectName) {
		this.reqProjectName = reqProjectName;
	}

	//Generating getters & Setters

	public int getNumAssigned() {
		return numAssigned;
	}
	public void setNumAssigned(int numAssigned) {
		this.numAssigned = numAssigned;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getReqRmId() {
		return reqRmId;
	}
	public void setReqRmId(String reqRmId) {
		this.reqRmId = reqRmId;
	}
	public String getReqProjectId() {
		return reqProjectId;
	}
	public void setReqProjectId(String reqProjectId) {
		this.reqProjectId = reqProjectId;
	}
	public String getReqDateCreated() {
		return reqDateCreated;
	}
	public void setReqDateCreated(String reqDateCreated) {
		this.reqDateCreated = reqDateCreated;
	}
	public String getReqDateClosed() {
		return reqDateClosed;
	}
	public void setReqDateClosed(String reqDateClosed) {
		this.reqDateClosed = reqDateClosed;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getReqVacancyName() {
		return reqVacancyName;
	}
	public void setReqVacancyName(String reqVacancyName) {
		this.reqVacancyName = reqVacancyName;
	}
	public String getReqSkill() {
		return reqSkill;
	}
	public void setReqSkill(String reqSkill) {
		this.reqSkill = reqSkill;
	}
	public String getReqDomain() {
		return reqDomain;
	}
	public void setReqDomain(String reqDomain) {
		this.reqDomain = reqDomain;
	}
	public int getReqNoReq() {
		return reqNoReq;
	}
	public void setReqNoReq(int reqNoReq) {
		this.reqNoReq = reqNoReq;
	}	

	//
}
