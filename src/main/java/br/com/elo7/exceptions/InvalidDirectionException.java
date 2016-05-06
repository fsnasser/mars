package br.com.elo7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Direction of Probe is invalid. Check the documentation.")
public class InvalidDirectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDirectionException() { super(); }
	public InvalidDirectionException(String message) { super(message); }
	public InvalidDirectionException(String message, Throwable cause) { super(message, cause); }
	public InvalidDirectionException(Throwable cause) { super(cause); }
	
}
