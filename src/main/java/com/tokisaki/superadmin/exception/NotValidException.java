package com.tokisaki.superadmin.exception;

public class NotValidException extends RuntimeException {
	private static final long serialVersionUID = -6230587222337328377L;

	public NotValidException() {
	}

	public NotValidException(String msg) {
		super(msg);
	}
}