package br.com.elo7;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.elo7.business.ApplicationBusiness;
import br.com.elo7.entities.CustomResponseStatus;
import br.com.elo7.entities.PlateauResponse;
import br.com.elo7.exceptions.InvalidCoordinateException;
import br.com.elo7.exceptions.PlateauNotFoundException;
import br.com.elo7.exceptions.ProbeNotFoundException;
import br.com.elo7.exceptions.ProbeOutPlateauException;
import br.com.elo7.exceptions.ProbeSequenceException;

public class ProbeTests {

	private ApplicationBusiness business;
	
	@Before
	public void init() {
		business = new ApplicationBusiness();
	}
	
	@Test
	public void whenValidProbeIsLanded() throws PlateauNotFoundException, ProbeOutPlateauException, InvalidCoordinateException, ProbeSequenceException {
		business.initPlateau(6, 5);
		CustomResponseStatus response = business.initProbeOnPlateau(1, 3, 'N', "LMLMLMLMM");
		ValidationUtils.createdValidation(response);
	}
	
	@Test
	public void whenGetValidPlateauStatusWithProbe() throws PlateauNotFoundException, ProbeOutPlateauException, InvalidCoordinateException, ProbeSequenceException {
		business.initPlateau(6, 5);
		business.initProbeOnPlateau(1, 3, 'N', "LMLMLMLMM");
		business.initProbeOnPlateau(3, 5, 'S', "LRMLLMMMMR");
		PlateauResponse response = (PlateauResponse) business.getPlateauStatus();		
		ValidationUtils.ContentValidation(response);
		assertThat(response.getPlateau().getProbeCoordinates().get(0), equalTo("1 3 N"));
		assertThat(response.getPlateau().getProbeCoordinates().get(1), equalTo("3 5 S"));
	}
	
	@Test
	public void whenMoveProbe() throws PlateauNotFoundException, ProbeNotFoundException, ProbeSequenceException, ProbeOutPlateauException, InvalidCoordinateException {
		business.initPlateau(6, 5);
		business.initProbeOnPlateau(1, 3, 'N', "LMLMLMLMM");
		CustomResponseStatus response = business.moveProbes();
		ValidationUtils.OKValidation(response);
	}
	
	@Test
	public void whenGetStatusAfterMoveProbe() throws PlateauNotFoundException, ProbeOutPlateauException, InvalidCoordinateException, ProbeSequenceException, ProbeNotFoundException {
		business.initPlateau(6, 5);
		business.initProbeOnPlateau(1, 2, 'N', "LMLMLMLMM");
		business.initProbeOnPlateau(3, 3, 'E', "MMRMMRMRRM");
		business.moveProbes();
		PlateauResponse response = (PlateauResponse) business.getPlateauStatus();
		ValidationUtils.ContentValidation(response);
		assertThat(response.getPlateau().getProbeCoordinates().get(0), equalTo("1 3 N"));
		assertThat(response.getPlateau().getProbeCoordinates().get(1), equalTo("5 1 E"));
	}	
	
	
}
