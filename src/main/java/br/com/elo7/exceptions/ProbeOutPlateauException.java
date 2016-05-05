package br.com.elo7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Probe out of Plateau.")
public class ProbeOutPlateauException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProbeOutPlateauException() { super(); }
	public ProbeOutPlateauException(String message) { super(message); }
	public ProbeOutPlateauException(String message, Throwable cause) { super(message, cause); }
	public ProbeOutPlateauException(Throwable cause) { super(cause); }
	
}
