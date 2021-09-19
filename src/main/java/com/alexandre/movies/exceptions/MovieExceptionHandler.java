package com.alexandre.movies.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MovieExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String SERVER_ERROR = "Server error";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return ResponseEntity.badRequest().body(SERVER_ERROR);
	}

	@ExceptionHandler(MovieException.class)
	public ResponseEntity<String> handleMovieException(Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
