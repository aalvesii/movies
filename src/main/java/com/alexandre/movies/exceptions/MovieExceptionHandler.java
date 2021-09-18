package com.alexandre.movies.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class MovieExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleError(Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
