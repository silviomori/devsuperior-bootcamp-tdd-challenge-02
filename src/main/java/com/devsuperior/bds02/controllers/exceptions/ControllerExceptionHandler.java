package com.devsuperior.bds02.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.exceptions.DatabaseIntegrityException;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFoundException(Exception e, HttpServletRequest request) {
		return createStandardError(e, request, HttpStatus.NOT_FOUND, "Resource not found");
	}

	@ExceptionHandler(DatabaseIntegrityException.class)
	public ResponseEntity<StandardError> databaseIntegrityException(Exception e, HttpServletRequest request) {
		return createStandardError(e, request, HttpStatus.BAD_REQUEST, "Database integrity violation");
	}

	private ResponseEntity<StandardError> createStandardError(Exception e, HttpServletRequest request,
			HttpStatus status, String error) {
		StandardError err = new StandardError();
		err.setStatus(status.value());
		err.setError(error);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
	}

}
