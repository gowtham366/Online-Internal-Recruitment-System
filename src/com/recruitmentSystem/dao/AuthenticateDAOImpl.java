package com.recruitmentSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.exception.OIRSException;
import com.recruitmentSystem.util.DBConnection;

public class AuthenticateDAOImpl implements IAuthenticateDAO {

	Connection connection = null;
	
	
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
		new DBConnection();
		// TODO Auto-generated method stub
		connection = DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet = null;
		UserBean bean = new UserBean();
		try {
			preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_AUTH_USER);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPass);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				preparedStatement = connection.prepareStatement(IQueryMapper.QUERY_UPDATE_LAST_LOGIN);
				preparedStatement.setString(1, userId);
				int queryResult=preparedStatement.executeUpdate();
				if(queryResult > 0){
					bean.setUserId(resultSet.getString(1));
					bean.setUserRole(resultSet.getString(2));
					bean.setLastLogin(resultSet.getString(3));	
				}
			}
			else
			{
				bean = null;	
			}
			return bean;
			
		} catch (SQLException e) {
			throw new OIRSException("Authentication Exception : "+e.getMessage());
		}
		
	}

}
