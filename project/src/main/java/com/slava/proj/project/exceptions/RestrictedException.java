package com.slava.proj.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class RestrictedException extends Exception {

	public RestrictedException(String message) {
		super(message);
	}
}
