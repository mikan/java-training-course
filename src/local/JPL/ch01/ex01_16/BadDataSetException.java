/*
 * Copyright(C) 2013 Yutaka Kato
 */
package local.JPL.ch01.ex01_16;

import java.io.IOException;

public class BadDataSetException extends Exception {
	
	private String setName;
	private IOException ioe;
	private static final long serialVersionUID = -3283120807291832327L;
	
	@SuppressWarnings("unused")
	private BadDataSetException() {
		super();
		// Private constructor
	}
	
	public BadDataSetException(String setName, IOException e) {
		super(setName + e.getCause());
		this.setName = setName;
		this.ioe = e;
	}
	
	public String getSetName() {
		return setName;
	}
	
	public IOException getIOExceptionContext() {
		return ioe;
	}
}
