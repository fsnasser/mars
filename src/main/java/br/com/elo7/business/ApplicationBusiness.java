package br.com.elo7.business;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.elo7.entities.CustomResponseStatus;
import br.com.elo7.entities.Plateau;
import br.com.elo7.entities.PlateauResponse;
import br.com.elo7.entities.Probe;
import br.com.elo7.exceptions.InvalidCoordinateException;
import br.com.elo7.exceptions.InvalidDirectionException;
import br.com.elo7.exceptions.NegativeCoordinateException;
import br.com.elo7.exceptions.PlateauNotFoundException;
import br.com.elo7.exceptions.PlateauNotRectangleException;
import br.com.elo7.exceptions.ProbeNotFoundException;
import br.com.elo7.exceptions.ProbeOutPlateauException;
import br.com.elo7.exceptions.ProbeSequenceException;

@Component
public class ApplicationBusiness {

	private static Plateau plateau;
	
	public CustomResponseStatus initPlateau(int x, int y) throws InvalidCoordinateException {
		CustomResponseStatus status;
		if(x >= 0 && y >= 0) {
			if(x != y) {
				plateau = new Plateau(x, y);
				status = new CustomResponseStatus(HttpStatus.CREATED);
			} else {
				throw new PlateauNotRectangleException();
			}
		} else {
			throw new NegativeCoordinateException();
		}
		return status;
	}
	
	public CustomResponseStatus getPlateauStatus() throws PlateauNotFoundException {
		CustomResponseStatus status;
		if(plateau != null) {
			CustomResponseStatus responseStatus = new CustomResponseStatus(HttpStatus.OK);
			status = new PlateauResponse(responseStatus, plateau);
		} else {
			throw new PlateauNotFoundException();
		}
		return status;
	}
	
	public CustomResponseStatus initProbeOnPlateau(int x, int y, char direction, String sequence) throws PlateauNotFoundException, ProbeOutPlateauException, InvalidCoordinateException, ProbeSequenceException, InvalidDirectionException {
		CustomResponseStatus status;
		if(plateau != null) {
			if(x >= 0 && y >= 0) {				
				Probe probe = new Probe(x, y, direction, sequence);					
				plateau.addProbe(probe);
				status = new CustomResponseStatus(HttpStatus.CREATED);
				return status;					
			} else {
				throw new NegativeCoordinateException();
			}			
		} else {
			throw new PlateauNotFoundException();
		}		
	}
	
	public CustomResponseStatus moveProbes() throws PlateauNotFoundException, ProbeNotFoundException, ProbeOutPlateauException {
		CustomResponseStatus status;
		if(plateau != null) {
			if(plateau.getProbes().size() > 0) {
				for (Probe probe : plateau.getProbes()) {
					probe.moveOnPlateau(plateau);
				}
				status = new CustomResponseStatus(HttpStatus.OK);
			} else {
				throw new ProbeNotFoundException();
			}
		} else {
			throw new PlateauNotFoundException();
		}
		return status;
	}

}
