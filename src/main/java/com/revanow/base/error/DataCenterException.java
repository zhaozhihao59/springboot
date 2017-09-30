package com.revanow.base.error;

public class DataCenterException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataCenterException(Object obj){
		 super(obj.toString());
	}
}
