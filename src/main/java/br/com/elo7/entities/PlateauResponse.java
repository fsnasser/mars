package br.com.elo7.entities;

import org.springframework.http.ResponseEntity;

public class PlateauResponse extends CustomResponseStatus {

private Plateau plateau;
	
	public PlateauResponse(CustomResponseStatus status, Plateau plateau) {
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
	public ResponseEntity<CustomResponseStatus> makeResponseEntity() {
		return new ResponseEntity<CustomResponseStatus>(this, super.getType());
	}
	
}
