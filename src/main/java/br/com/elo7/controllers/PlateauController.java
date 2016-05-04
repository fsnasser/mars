package br.com.elo7.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.business.ApplicationBusiness;
import br.com.elo7.entities.ResponseStatus;

@RestController
@RequestMapping(value = "plateau")
public class PlateauController {
	
	private ApplicationBusiness business;
	
	public PlateauController() {
		this.business = new ApplicationBusiness();
	}
	
	@RequestMapping(value = "init", method = RequestMethod.POST)
	public ResponseEntity<ResponseStatus> initPlateau(Long sizeX, Long sizeY) {
		ResponseStatus response = business.initPlateau(sizeX, sizeY);
		return response.makeResponseEntity();
	}
	
	@RequestMapping(value = "status", method = RequestMethod.GET)
	public ResponseEntity<ResponseStatus> plateauStatus() {
		ResponseStatus response = business.getPlateauStatus();
		return response.makeResponseEntity();
	}
	
}
