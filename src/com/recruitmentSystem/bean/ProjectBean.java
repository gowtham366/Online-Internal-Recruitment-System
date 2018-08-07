package com.recruitmentSystem.bean;

import java.util.Date;

public class ProjectBean {

private String projectId;
private String projectName;
private String projectDesc;
private String projectStartDate;
private String projectEndDate;
private String projectRmId;
private String projectStatus;

public String getProjectStatus() {
	return projectStatus;
}
public void setProjectStatus(String projectStatus) {
	this.projectStatus = projectStatus;
}
//Generating getters & Setters
public String getProjectId() {
	return projectId;
}
public void setProjectId(String projectId) {
	this.projectId = projectId;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}
public String getProjectDesc() {
	return projectDesc;
}
public void setProjectDesc(String projectDesc) {
	this.projectDesc = projectDesc;
}
public String getProjectStartDate() {
	return projectStartDate;
}
public void setProjectStartDate(String projectStartDate) {
	this.projectStartDate = projectStartDate;
}
public String getProjectEndDate() {
	return projectEndDate;
}
public void setProjectEndDate(String projectEndDate) {
	this.projectEndDate = projectEndDate;
}
public String getProjectRmId() {
	return projectRmId;
}
public void setProjectRmId(String projectRmId) {
	this.projectRmId = projectRmId;
}

}
