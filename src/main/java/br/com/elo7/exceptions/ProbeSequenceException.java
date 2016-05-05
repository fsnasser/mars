package br.com.elo7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some probe movement is invalid. See documentation.")
public class ProbeSequenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProbeSequenceException() { super(); }
	public ProbeSequenceException(String message) { super(message); }
	public ProbeSequenceException(String message, Throwable cause) { super(message, cause); }
	public ProbeSequenceException(Throwable cause) { super(cause); }
	
}
