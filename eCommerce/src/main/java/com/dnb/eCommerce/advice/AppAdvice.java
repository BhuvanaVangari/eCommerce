package com.dnb.eCommerce.advice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.dnb.eCommerce.exceptions.IdNotFoundException;
import com.dnb.eCommerce.exceptions.InvalidIdException;
import com.dnb.eCommerce.exceptions.NotUniqueNameException;

@ControllerAdvice
public class AppAdvice {

	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<?> invalidIdExceptionHandler(InvalidIdException e) {
		Map<String, String>map=new HashMap<>();
		map.put("message", "Invalid id provided");
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e) {
		Map<String, String>map=new HashMap<>();
		map.put("message", "Id not Found");
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotUniqueNameException.class)
	public ResponseEntity<?> notUniqueNameExceptionHandler(NotUniqueNameException e){
		Map<String, String>map=new HashMap<>();
		map.put("message", "Product name not unique");
		map.put("HttpStatus", HttpStatus.INTERNAL_SERVER_ERROR+"");
		return new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleException(HttpRequestMethodNotSupportedException e)
			throws IOException {
		String provided = e.getMethod();
		List<String> supported = List.of(e.getSupportedMethods());
		String error = provided + " is not one of the HTTP supported methods (" + String.join(", ", supported) + ")";
		Map<String, String> errorResponse = Map.of("error", error, "message", e.getLocalizedMessage(), "status",
				HttpStatus.METHOD_NOT_ALLOWED.toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, TypeMismatchException.class })
	public ResponseEntity<Map<String, String>> handleException(TypeMismatchException e) {
		Map<String, String> map = Map.of("message", e.getLocalizedMessage(), "HttpStatus",
				HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
	
}
