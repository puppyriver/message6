package com.alcatelsbell.nms.valueobject.odn.exception;

import java.rmi.RemoteException;

public class OdnException extends RemoteException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OdnException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OdnException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OdnException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
