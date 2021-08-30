package com.rjnitt.exception;

public class NoBookingFound extends RuntimeException {

	public NoBookingFound(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
