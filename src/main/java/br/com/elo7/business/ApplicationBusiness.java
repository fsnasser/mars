package br.com.elo7.business;

import org.springframework.http.HttpStatus;

import br.com.elo7.entities.Plateau;
import br.com.elo7.entities.PlateauResponse;
import br.com.elo7.entities.ResponseStatus;

public class ApplicationBusiness {

	private Plateau plateau;
	
	public ResponseStatus initPlateau(Long xSize, Long ySize) {
		ResponseStatus status;
		if(xSize != null && ySize != null) {
			if(xSize > 0 && ySize > 0) {
				if(xSize != ySize) {
					plateau = new Plateau(xSize, ySize);
					status = new ResponseStatus(HttpStatus.CREATED);
				} else {
					status = new ResponseStatus(HttpStatus.BAD_REQUEST, "Parâmetro 'x' precisa ser diferente de 'y'");
				}
			} else {
				status = new ResponseStatus(HttpStatus.BAD_REQUEST, "Parâmetros 'x' e 'y' não podem ser menores do que zero");
			}
		} else {
			status = new ResponseStatus(HttpStatus.BAD_REQUEST, "Parâmetro 'x' ou 'y' não enviado");
		}
		return status;
	}
	
	public ResponseStatus getPlateauStatus() {
		ResponseStatus status;
		if(plateau != null) {
			ResponseStatus responseStatus = new ResponseStatus(HttpStatus.OK);
			status = new PlateauResponse(responseStatus, plateau);
		} else {
			status = new ResponseStatus(HttpStatus.ACCEPTED, "Necessário iniciar o planalto");
		}
		return status;
	}

}
