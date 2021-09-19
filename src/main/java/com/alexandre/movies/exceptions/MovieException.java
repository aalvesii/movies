package com.alexandre.movies.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class MovieException extends Exception {

	private static final long serialVersionUID = 1L;

	public MovieException(String message) {
		super(message);
	}

	public MovieException(BindingResult result) {
		super(getErrosMessages(result));
	}

	private static String getErrosMessages(BindingResult result) {
		List<ObjectError> errors = result.getAllErrors();

		return errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
	}

}
