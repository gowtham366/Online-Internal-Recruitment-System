package com.recruitmentSystem.dao;

import java.util.List;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.exception.OIRSException;

public interface IAdminDAO {
	public String addNewUser(UserBean userBean) throws OIRSException;
	public String assignRole(String userId,String role) throws OIRSException;
	public String deleteUser(String userId) throws OIRSException;	
	public List<String> getUserIds() throws OIRSException;
	public List<UserBean> getUserDetails() throws OIRSException;
	public void generateReport() throws OIRSException;
}
