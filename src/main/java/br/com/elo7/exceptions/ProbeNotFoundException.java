package br.com.elo7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ACCEPTED, reason = "No initialized probe")
public class ProbeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProbeNotFoundException() { super(); }
	public ProbeNotFoundException(String message) { super(message); }
	public ProbeNotFoundException(String message, Throwable cause) { super(message, cause); }
	public ProbeNotFoundException(Throwable cause) { super(cause); }
	
}
