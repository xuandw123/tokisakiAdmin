package com.tokisaki.superadmin.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6230587222337328377L;

	public NotFoundException() {
	}

	public NotFoundException(String item, String itemId) {
		super(item + ": " + itemId + " not found.");
	}
}