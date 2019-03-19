package com.diogo.franchi.money.transfer.model;

public class Error {

    private final int httpStatusCode;
    private final String message;

    public Error(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getMessage() {
		return message;
	}
    
    

}