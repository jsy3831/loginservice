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

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response handleException(Exception e) {
		log.error("handleException throw Exception : {}", e.getMessage());
		return new Response(e.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response handleConstraintViolationException(ConstraintViolationException e) {
		log.error("handleConstraintViolationException throw ConstraintViolationException : {}", e.getMessage());
		return new Response(e.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response handleIllegalArgumentException(IllegalArgumentException e) {
		log.error("handleIllegalArgumentException throw IllegalArgumentException : {}", e.getMessage());
		return new Response(e.getMessage());
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Response handleUnauthorizedException(UnauthorizedException e) {
		return new Response(e.getMessage());
	}

	@ExceptionHandler(DuplicatedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Response handleDuplicatedException(DuplicatedException e) {
		return new Response(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		BindingResult bindingResult = e.getBindingResult();
		List<String> message = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			message.add(fieldError.getDefaultMessage());
		}

		return new Response(message.toString());
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Response handleAuthenticationException(AuthenticationException e) {
		return new Response(e.getMessage());
	}
}
