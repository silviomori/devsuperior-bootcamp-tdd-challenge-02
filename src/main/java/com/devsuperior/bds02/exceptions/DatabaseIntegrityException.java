package com.devsuperior.bds02.exceptions;

public class DatabaseIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseIntegrityException(String message) {
		super(message);
	}

}
