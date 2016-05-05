package br.com.elo7.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.business.ApplicationBusiness;
import br.com.elo7.entities.CustomResponseStatus;
import br.com.elo7.exceptions.InvalidCoordinateException;
import br.com.elo7.exceptions.PlateauNotFoundException;
import br.com.elo7.exceptions.ProbeNotFoundException;
import br.com.elo7.exceptions.ProbeOutPlateauException;
import br.com.elo7.exceptions.ProbeSequenceException;

@RestController
@RequestMapping(value = "probe")
public class ProbeController {
	
	private ApplicationBusiness business;
	
	public ProbeController() {
		this.business = new ApplicationBusiness();
	}
	
	@RequestMapping(value = "init", method = RequestMethod.POST)
	public ResponseEntity<CustomResponseStatus> initProbe(
			@RequestParam(value="x", required=true) int x, 
			@RequestParam(value="y", required=true) int y, 
			@RequestParam(value="direction", required=true) char direction, 
			@RequestParam(value="mSequence", required=true) String mSequence)
			throws PlateauNotFoundException, ProbeOutPlateauException, InvalidCoordinateException, ProbeSequenceException {
		CustomResponseStatus response = business.initProbeOnPlateau(x, y, direction, mSequence);
		return response.makeResponseEntity();
	}
	
	@RequestMapping(value = "move", method = RequestMethod.GET)
	public ResponseEntity<CustomResponseStatus> moveProbes()
			throws PlateauNotFoundException, ProbeNotFoundException, ProbeSequenceException, ProbeOutPlateauException {
		CustomResponseStatus response = business.moveProbes();
		return response.makeResponseEntity();
	}
	
}
