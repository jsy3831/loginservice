package com.photobook.controller.response;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Response<T> {

	private final LocalDateTime timestamp = LocalDateTime.now();

	private String message;

	private T body;

	public Response(String message) {
		this.message = message;
	}

	public Response(String message, T body) {
		this.message = message;
		this.body = body;
	}
}
