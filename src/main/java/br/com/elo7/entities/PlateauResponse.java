package br.com.elo7.entities;

import org.springframework.http.ResponseEntity;

public class PlateauResponse extends ResponseStatus {

	private Plateau plateau;
	
	public PlateauResponse(ResponseStatus status, Plateau plateau) {
		super(status.getType(), status.getMessage());
		this.plateau = plateau;
	}	

	public Plateau getPlateau() {
		return plateau;
	}
	
	@Override
	public Integer getCode() {
		return null;
	}

	@Override
	public String getMessage() {
		return null;		
	}
	
	@Override
	public String getError() {
		return null;
	}
	
	@Override
	public ResponseEntity<ResponseStatus> makeResponseEntity() {
		return new ResponseEntity<ResponseStatus>(this, super.getType());
	}
	
}
