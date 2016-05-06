package br.com.elo7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.business.ApplicationBusiness;
import br.com.elo7.entities.CustomResponseStatus;
import br.com.elo7.exceptions.InvalidCoordinateException;
import br.com.elo7.exceptions.PlateauNotFoundException;

@RestController
@RequestMapping(value = "plateau")
public class PlateauController {
	
	@Autowired
	private ApplicationBusiness business;
	
	@RequestMapping(value = "init", method = RequestMethod.POST)
	public ResponseEntity<CustomResponseStatus> initPlateau(
			@RequestParam(value = "x", required = true) int x,
			@RequestParam(value = "y", required = true) int y)
			throws InvalidCoordinateException {
		CustomResponseStatus response = business.initPlateau(x, y);
		return response.makeResponseEntity();
	}
	
	@RequestMapping(value = "status", method = RequestMethod.GET)
	public ResponseEntity<CustomResponseStatus> plateauStatus() throws PlateauNotFoundException {
		CustomResponseStatus response = business.getPlateauStatus();
		return response.makeResponseEntity();
	}
	
}
