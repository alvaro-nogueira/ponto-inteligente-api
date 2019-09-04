package com.alvaro.pontoInteligente.api.exception;

import java.util.List;

import org.springframework.validation.ObjectError;

public class ApiValidationException extends RuntimeException {

	private static final long serialVersionUID = 7732150254799344098L;

	private List<ObjectError> errors;

	public ApiValidationException(String msg, List<ObjectError> errors) {

		super(msg);
		this.errors = errors;

	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}

}