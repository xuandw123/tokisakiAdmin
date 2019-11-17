package com.tokisaki.superadmin.exception;

public class InvalidInputParamException extends RuntimeException {
	private static final long serialVersionUID = -6230587222337328377L;

	public InvalidInputParamException(String msg) {
		super(msg);
	}
}
