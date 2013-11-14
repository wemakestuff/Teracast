package com.wemakestuff.teracast.rss.exception;

public class InvalidBooleanException extends Exception {

	public InvalidBooleanException() {
	}

	public InvalidBooleanException(final String detailMessage) {
		super(detailMessage);
	}

	public InvalidBooleanException(final String detailMessage, final Throwable throwable) {
		super(detailMessage, throwable);
	}

	public InvalidBooleanException(final Throwable throwable) {
		super(throwable);
	}
}
