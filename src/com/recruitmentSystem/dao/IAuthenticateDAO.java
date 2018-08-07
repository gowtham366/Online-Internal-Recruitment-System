package com.recruitmentSystem.dao;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.exception.OIRSException;

public interface IAuthenticateDAO {
	public UserBean loginUser(String userId,String userPass) throws OIRSException;
}
