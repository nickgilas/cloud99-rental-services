package com.cloud99.rental.service;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -4329345962753473854L;

	public ServiceException(String messageCode) {
		super(messageCode);
	}

}
