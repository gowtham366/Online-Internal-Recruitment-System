package com.recruitmentSystem.service;

import java.util.List;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.exception.OIRSException;

public interface IAdminService {
	public String addNewUser(UserBean userBean) throws OIRSException;
	public String assignRole(String userId,String role) throws OIRSException;
	public String deleteUser(String userId) throws OIRSException;	
	public List<String> getUserIds() throws OIRSException;
	public List<UserBean> getUserDetails() throws OIRSException;
	public void generateReport() throws OIRSException;
	public String encryptPassword(String password) throws OIRSException;
	
	public boolean isValidUserid(String uid);
	public boolean isValidPassword(String pwd);
	
}
