package com.anagrams.json.exception;

public class JsonRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -9061776511307732945L;
	
	public JsonRuntimeException(Throwable t) {
		super(t);
	}
}
