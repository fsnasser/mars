package br.com.elo7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Plateau not initialized")
public class PlateauNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlateauNotFoundException() { super(); }
	public PlateauNotFoundException(String message) { super(message); }
	public PlateauNotFoundException(String message, Throwable cause) { super(message, cause); }
	public PlateauNotFoundException(Throwable cause) { super(cause); }
	
}
