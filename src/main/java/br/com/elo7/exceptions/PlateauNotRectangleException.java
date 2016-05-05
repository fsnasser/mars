package br.com.elo7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "X can not be equal Y")
public class PlateauNotRectangleException extends InvalidCoordinateException {

	private static final long serialVersionUID = 1L;

	public PlateauNotRectangleException() { super(); }
	public PlateauNotRectangleException(String message) { super(message); }
	public PlateauNotRectangleException(String message, Throwable cause) { super(message, cause); }
	public PlateauNotRectangleException(Throwable cause) { super(cause); }
	
}
