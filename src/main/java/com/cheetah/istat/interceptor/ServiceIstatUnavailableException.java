package com.cheetah.istat.interceptor;

public class ServiceIstatUnavailableException extends Exception {


	public ServiceIstatUnavailableException() {
		super();
	}

	public ServiceIstatUnavailableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceIstatUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceIstatUnavailableException(String message) {
		super(message);
	}

	public ServiceIstatUnavailableException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
