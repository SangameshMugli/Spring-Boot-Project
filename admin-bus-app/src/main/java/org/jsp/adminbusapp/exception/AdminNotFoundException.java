package org.jsp.adminbusapp.exception;



public class AdminNotFoundException extends RuntimeException{
	
@Override
public String getMessage() {
	return "Admin not found";
}
}
