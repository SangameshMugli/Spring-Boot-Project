package org.jsp.adminbusapp.exception;

import org.jsp.adminbusapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdminBusExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleANFE(AdminNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData(exception.getMessage());
		structure.setMessage("Admin not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleBNFE(BusNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData("Bus Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	

}
