package com.photobook.controller.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Response<T> {

	private final LocalDateTime timestamp = LocalDateTime.now();

	private final int code;

	private final String message;

	private final T body;

	@Builder
	Response(final int code, final String message, final T body) {
		this.code = code;
		this.message = message;
		this.body = body;
	}

	public static <T> Response<T> toResponse(int code, String message) {
		return toResponse(code, message, null);
	}

	public static <T> Response<T> toResponse(int code, String message, T body) {
		return Response.<T>builder()
			.code(code)
			.message(message)
			.body(body)
			.build();
	}
}
