package com.photobook.exception.customexception;

public class DuplicatedException extends RuntimeException {

	public DuplicatedException(String message) {
		super(message);
	}
}
