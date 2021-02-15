package com.assignment.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.assignment.spring.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
	private static final String EXCEPTION_IN_PROCESSING_REQUEST = "Exception in processing request";

	@ExceptionHandler(NotFoundException.class)
	public void handleNotFoundExceptionException(NotFoundException ex, HttpServletResponse res) throws IOException {
		log.debug("Could not find entity", ex);
		res.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public void handleHttpServerErrorException(HttpServerErrorException ex, HttpServletResponse res) throws IOException {
		log.error(EXCEPTION_IN_PROCESSING_REQUEST, ex);
		res.sendError(ex.getStatusCode().value(), ex.getMessage());
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public void handleHttpClientErrorException(HttpClientErrorException ex, HttpServletResponse res) throws IOException {
		log.error(EXCEPTION_IN_PROCESSING_REQUEST, ex);
		res.sendError(ex.getStatusCode().value(), ex.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public void handleIllegalArgumentException(IllegalArgumentException ex, HttpServletResponse res) throws IOException {
		log.error(EXCEPTION_IN_PROCESSING_REQUEST, ex);
		res.sendError(HttpStatus.BAD_REQUEST.value(), "Insufficient Authentication");
	}

	@ExceptionHandler(Exception.class)
	public void handleException(Exception ex, HttpServletResponse res) throws IOException {
		log.error(EXCEPTION_IN_PROCESSING_REQUEST, ex);
		res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
	}
}

