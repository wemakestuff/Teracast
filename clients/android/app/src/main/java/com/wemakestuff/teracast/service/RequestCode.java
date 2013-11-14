package com.wemakestuff.teracast.service;

public enum RequestCode {
	DOWNLOAD_RSS_FEED(100);

	private int requestCode;

	RequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public int getRequestCode() {
		return requestCode;
	}
}