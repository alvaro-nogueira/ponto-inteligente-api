package com.alvaro.pontoInteligente.api.exception;

public final class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7051464830497337195L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

	public ResourceNotFoundException(final Throwable cause) {
		super(cause);
	}

}
