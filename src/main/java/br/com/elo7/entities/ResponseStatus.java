package br.com.elo7.entities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseStatus {

	private String message;
	
	private HttpStatus type;

	public ResponseStatus(HttpStatus type) {
		this.type = type;
	}
	
	public ResponseStatus(HttpStatus type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public Integer getCode() {
		if(type != null) {
			return type.value();
		}
		return null;
	}

	public String getMessage() {
		if(message != null) {
			return message; 
		} else if(type != null) {
			return type.getReasonPhrase();
		} else {
			return null;
		}		
	}
	
	@JsonIgnore
	public HttpStatus getType() {
		return type;
	}

	public String getError() {
		if(type != null && !type.is2xxSuccessful()) {
			return type.getReasonPhrase();
		}
		return null;
	}
	
	public ResponseEntity<ResponseStatus> makeResponseEntity() {
		return new ResponseEntity<ResponseStatus>(this, type);
	}
	
}
