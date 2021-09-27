package com.photobook.exception;

import lombok.Getter;

@Getter
public class ExceptionType {

	private final String type;

	ExceptionType(final String type) {
		this.type = type;
	}

}
