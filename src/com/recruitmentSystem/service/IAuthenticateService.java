package com.recruitmentSystem.service;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.exception.OIRSException;

public interface IAuthenticateService {
	public UserBean loginUser(String userId,String userPass) throws OIRSException;
}
