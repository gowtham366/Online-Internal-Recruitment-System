package com.recruitmentSystem.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.recruitmentSystem.exception.OIRSException;



/**
 * @author gowthc
 *
 */
public class DBConnection {

	private static Connection connection;


	public static Connection getConnection() throws OIRSException{
		Connection conn=null;
			try {
				InitialContext ic=new InitialContext();
				DataSource ds=(DataSource) ic.lookup("java:/OracleDS");
			conn=ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OIRSException(e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OIRSException(e.getMessage());
			
		}
			return conn;
			
		}
}
