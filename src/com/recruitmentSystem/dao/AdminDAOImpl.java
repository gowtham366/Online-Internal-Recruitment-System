package com.recruitmentSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.exception.OIRSException;
import com.recruitmentSystem.util.DBConnection;

public class AdminDAOImpl implements IAdminDAO{

	UserBean userbean =new UserBean();

	PreparedStatement preparedstatement=null;		
	ResultSet resultSet = null;
	int queryResult=0;

	
	/*******************************************************************************************************
	 - Function Name	:	addNewUser()
	 - Input Parameters	:	userBean
	 - Return Type		:	String
	 - Throws		    :  	OIRSException
	 - Author	     	:	SINDHU, SIREESHA
	 - Creation Date	:	07/07/2018
	 - Description		:	return String
	 ********************************************************************************************************/
	@Override
	public String addNewUser(UserBean userBean) throws OIRSException {
		Connection connection = DBConnection.getConnection();	
		String result=null;
		int rs;
		try{
			preparedstatement=connection.prepareStatement(IQueryMapper.ADDUSER);
			preparedstatement.setString(1, userBean.getUserId());
			preparedstatement.setString(2, userBean.getUserPassword());
			preparedstatement.setString(3, userBean.getUserRole());
			preparedstatement.setString(4, userBean.getHint());
			rs = preparedstatement.executeUpdate();
			if(rs > 0)
			{
				result = "User Added Successfully!!!";
			}
			else
			{
				throw new OIRSException("Inserting user details failed ");

			}
			return result;
		}catch(SQLException sqlexception)
		{
			throw new OIRSException("SQL error occured "+sqlexception.getMessage());
		}

		finally
		{
			try 
			{

				preparedstatement.close();
				connection.close();
			}
			catch (SQLException sqlException) 
			{

				throw new OIRSException("Error in closing db connection");

			}
		}
	}

	
	
	/*******************************************************************************************************
	 - Function Name	:	assignRole()
	 - Input Parameters	:	userId, role
	 - Return Type		:	String
	 - Throws		    :  	OIRSException
	 - Author	     	:	SINDHU, SIREESHA
	 - Creation Date	:	07/07/2018
	 - Description		:	return String
	 ********************************************************************************************************/
	@Override
	public String assignRole(String userId, String role) throws OIRSException {

		Connection connection = DBConnection.getConnection();
		String result=null;
		
		try{
			
			preparedstatement=connection.prepareStatement(IQueryMapper.ASSIGNROLE);
			preparedstatement.setString(1,role);
			preparedstatement.setString(2, userId);
			
			int queryResult = preparedstatement.executeUpdate();
			
			if(queryResult!=0){
				result = "Role updated Successfully for "+userId;
				System.out.println(result);
			}
			else
			{
				System.out.println("Updation failed");
			}
		}catch(Exception e)
		{
			throw new OIRSException("Error "+e.getMessage());
		}
		finally
		{
			try 
			{
				connection.close();
			} 
			catch (SQLException e) 
			{

				throw new OIRSException("Error in closing db connection"+e.getMessage());

			}
		}
		return result;

	}


	/*******************************************************************************************************
	 - Function Name	:	deleteUser()
	 - Input Parameters	:	userId
	 - Return Type		:	String
	 - Throws		    :  	OIRSException
	 - Author	     	:	SINDHU, SIREESHA
	 - Creation Date	:	07/07/2018
	 - Description		:	return String
	 ********************************************************************************************************/
	@Override
	public String deleteUser(String userId) throws OIRSException {
		Connection connection = DBConnection.getConnection();
		String result=null;
		try{
			result=userbean.getUserId();
			preparedstatement=connection.prepareStatement(IQueryMapper.DELETEUSER);
			preparedstatement.setString(1,userId);
			int r = preparedstatement.executeUpdate();
			if(r > 0){
				result = userId+" deleted!!!";
			}
			else
			{
				System.out.println("Delete failed");
			}
		}catch(Exception e)
		{
			throw new OIRSException("Error in closing db connection");
		}
		finally
		{
			try 
			{
				connection.close();
			} 
			catch (SQLException e) 
			{

				throw new OIRSException("Error in closing db connection"+e.getMessage());

			}
		}
		return result;

	}



	@Override
	public void generateReport() throws OIRSException {
		// TODO Auto-generated method stub

	}
	
	
	
	/*******************************************************************************************************
	 - Function Name	:	getUserId()
	 - Input Parameters	:	
	 - Return Type		:	List<String>
	 - Throws		    :  	OIRSException
	 - Author	     	:	SINDHU, SIREESHA
	 - Creation Date	:	07/07/2018
	 - Description		:	return String
	 ********************************************************************************************************/
	@Override
	public List<String> getUserIds() throws OIRSException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		Connection connection = DBConnection.getConnection();
		try {
			preparedstatement=connection.prepareStatement(IQueryMapper.GET_USER_IDS);
			resultSet=preparedstatement.executeQuery();
			while(resultSet.next()){
				list.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<UserBean> getUserDetails() throws OIRSException {
		// TODO Auto-generated method stub
		List<UserBean> list = new ArrayList<>();
		Connection connection = DBConnection.getConnection();
		try {
			preparedstatement=connection.prepareStatement(IQueryMapper.GET_USER_DETAILS);
			resultSet=preparedstatement.executeQuery();
			while(resultSet.next()){
				UserBean bean = new UserBean();
				bean.setUserId(resultSet.getString(1));
				bean.setUserRole(resultSet.getString(2));
				bean.setLastLogin(resultSet.getString(3));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OIRSException(e.getMessage());
		}
		return list;
	}

}
