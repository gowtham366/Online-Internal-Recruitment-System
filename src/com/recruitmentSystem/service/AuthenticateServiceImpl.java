package com.recruitmentSystem.service;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.dao.AuthenticateDAOImpl;
import com.recruitmentSystem.dao.IAuthenticateDAO;
import com.recruitmentSystem.exception.OIRSException;

public class AuthenticateServiceImpl implements IAuthenticateService {

	IAuthenticateDAO iAuthenticateDAO = new AuthenticateDAOImpl();
	UserBean bean = null;
	
	/*******************************************************************************************************
	 - Function Name	:	loginUser()
	 - Input Parameters	:	userId, userPass
	 - Return Type		:	userBean
	 - Throws		    :  	OIRSException
	 - Author	     	:	GOWTHAM, DIVYA
	 - Creation Date	:	07/07/2018
	 - Description		:	Validating User based on Id and PassWord Produced
	 ********************************************************************************************************/
	@Override
	public UserBean loginUser(String userId, String userPass)
			throws OIRSException {
		// TODO Auto-generated method stub
		bean = iAuthenticateDAO.loginUser(userId, userPass);
		return bean;
	}

}
