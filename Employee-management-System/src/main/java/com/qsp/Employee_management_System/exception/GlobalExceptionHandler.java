package com.qsp.Employee_management_System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.qsp.Employee_management_System.responsestructure.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IdValidationException.class)
	public ResponseEntity<ResponseStructure<String>> invalidationExceptionhandler (IdValidationException id){
	ResponseStructure<String>rs=new ResponseStructure<>();
	rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
	rs.setMessage(""+id.getMessage());
	rs.setData(null);
	
	return new ResponseEntity<ResponseStructure<String>> (rs,HttpStatus.BAD_REQUEST);
	
   }
}
	
 

