package com.recruitmentSystem.exception;

public class OIRSException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7702458176365160755L;
	
	public OIRSException() {
		// TODO Auto-generated constructor stub
	}
	String errMsg;
	public OIRSException(String msg) {
		this.errMsg=msg;
	}
	
	@Override
	public String toString(){
		return errMsg;
	}
}
