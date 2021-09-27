package com.photobook.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.photobook.controller.response.Response;
import com.photobook.exception.customexception.DuplicatedException;
import com.photobook.exception.customexception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

	private static ExceptionType exceptionType(Exception e) {
		return new ExceptionType(e.getClass().getSimpleName());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<ExceptionType> handleException(Exception e) {
		log.error("handleException throw Exception : {}", e.getMessage());
		return Response.toResponse(400, e.getMessage(), exceptionType(e));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<ExceptionType> handleConstraintViolationException(ConstraintViolationException e) {
		log.error("handleConstraintViolationException throw ConstraintViolationException : {}", e.getMessage());
		return Response.toResponse(400, e.getMessage(), exceptionType(e));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<ExceptionType> handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("handleIllegalArgumentException throw IllegalArgumentException : {}", e.getMessage());
		return Response.toResponse(400, e.getMessage(), exceptionType(e));
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Response<ExceptionType> handleUnauthorizedException(UnauthorizedException e) {
		return Response.toResponse(401, e.getMessage(), exceptionType(e));
	}

	@ExceptionHandler(DuplicatedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Response<ExceptionType> handleDuplicatedException(DuplicatedException e) {
		return Response.toResponse(409, e.getMessage(), exceptionType(e));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response<ExceptionType> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		BindingResult bindingResult = e.getBindingResult();
		List<StringBuilder> message = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			StringBuilder builder = new StringBuilder();

			builder.append(fieldError.getField());
			builder.append(" : ");
			builder.append(fieldError.getDefaultMessage());

			message.add(builder);
		}

		return Response.toResponse(400, message.toString(), exceptionType(e));
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Response<ExceptionType> handleAuthenticationException(AuthenticationException e) {
		return Response.toResponse(403, e.getMessage(), exceptionType(e));
	}

}
