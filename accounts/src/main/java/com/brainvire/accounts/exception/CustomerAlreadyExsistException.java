package com.brainvire.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExsistException extends RuntimeException {
	public CustomerAlreadyExsistException(String message) {
		super(message);
	}
}
