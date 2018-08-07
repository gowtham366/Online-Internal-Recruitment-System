package com.recruitmentSystem.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.recruitmentSystem.bean.UserBean;
import com.recruitmentSystem.dao.AdminDAOImpl;
import com.recruitmentSystem.dao.IAdminDAO;
import com.recruitmentSystem.exception.OIRSException;

public class AdminService implements IAdminService{

	IAdminDAO iadmindao=new AdminDAOImpl();
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
		return iadmindao.addNewUser(userBean);

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
		return iadmindao.assignRole(userId, role);
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
		return iadmindao.deleteUser(userId);
	}

	@Override
	public void generateReport() throws OIRSException {

		return ;
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
	public boolean isValidUserid(String uid) {
		Pattern p=Pattern.compile("[A-Za-z0-9]{2,}");
		Matcher m=p.matcher(uid);
		boolean r=m.matches();
		if(m.matches())
			return true;
		else
		{
			System.err.println("Enter valid user id");
			return false;
		}


	}


	/*******************************************************************************************************
	 - Function Name	:	isValidPassword()
	 - Input Parameters	:	pwd
	 - Return Type		:	boolean
	 - Throws		    :  	OIRSException
	 - Author	     	:	SINDHU, SIREESHA
	 - Creation Date	:	07/07/2018
	 - Description		:	Validating Password
	 ********************************************************************************************************/
	@Override
	public boolean isValidPassword(String pwd) {
		Pattern p=Pattern.compile("[A-Za-z0-9]{5}");
		Matcher m=p.matcher(pwd);
		if(m.matches())
		{
			return true;
		}
		else
		{
			System.err.println("Invalid Password");
			return false;
		}
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
		List<String> list = iadmindao.getUserIds();
		return list;
	}

	@Override
	public List<UserBean> getUserDetails() throws OIRSException {
		// TODO Auto-generated method stub
		List<UserBean> list = iadmindao.getUserDetails(); 
		return list;
	}


	//encrypt password
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private static String SALT = "123456";
	@Override
	public String encryptPassword(String password) throws OIRSException {
		// TODO Auto-generated method stub
		String encryptedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(SALT.getBytes());        //Prepend SALT.
			md.update(password.getBytes());
			// md.update(SALT.getBytes());     // or append SALT.
			byte[] out = md.digest();
		    encryptedPassword = bytesToHex(out);            // Return the Hex Hash.
		    return encryptedPassword;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new OIRSException(e.getMessage());
		}
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	//encrypt password
}