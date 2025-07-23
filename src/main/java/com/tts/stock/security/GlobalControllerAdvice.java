package com.tts.stock.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalControllerAdvice {
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiResponse> handleContentNotAllowedException(UsernameNotFoundException userNotFound, HttpServletRequest httpRequest) {
		ApiResponse response = new ApiResponse();
		response.setPath(httpRequest.getRequestURI());
		response.setMessage(userNotFound.getMessage());
		response.setStatus(401);
		response.setError("User name or Password wrong");
		response.setTimestamp(new Date().getTime());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public void handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest httpRequest) {
		//LOGGER.error("MethodNot Support " + httpRequest.getLocalAddr() + " : " + httpRequest.getLocalName() + " : " +httpRequest.getRequestURI(), e.getMessage());	
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e, HttpServletRequest httpRequest) {
		//LOGGER.error("RuntimeException ", e);
		ApiResponse response = new ApiResponse();
		response.setPath(httpRequest.getRequestURI());
		response.setMessage(e.getMessage());
		response.setStatus(500);
		response.setError("Internal Server Error");
		//response.setTimestamp(new Date().getTime());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
