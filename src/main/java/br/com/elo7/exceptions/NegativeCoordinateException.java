package br.com.elo7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "X and Y need to be bigger then zero.")
public class NegativeCoordinateException extends InvalidCoordinateException {

	private static final long serialVersionUID = 1L;

	public NegativeCoordinateException() { super(); }
	public NegativeCoordinateException(String message) { super(message); }
	public NegativeCoordinateException(String message, Throwable cause) { super(message, cause); }
	public NegativeCoordinateException(Throwable cause) { super(cause); }
	
}
