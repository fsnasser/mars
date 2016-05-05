package br.com.elo7.exceptions;


public class InvalidCoordinateException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCoordinateException() { super(); }
	public InvalidCoordinateException(String message) { super(message); }
	public InvalidCoordinateException(String message, Throwable cause) { super(message, cause); }
	public InvalidCoordinateException(Throwable cause) { super(cause); }
	
}
