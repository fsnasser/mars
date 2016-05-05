package br.com.elo7;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.elo7.business.ApplicationBusiness;
import br.com.elo7.entities.PlateauResponse;
import br.com.elo7.entities.CustomResponseStatus;
import br.com.elo7.exceptions.InvalidCoordinateException;
import br.com.elo7.exceptions.NegativeCoordinateException;
import br.com.elo7.exceptions.PlateauNotFoundException;
import br.com.elo7.exceptions.PlateauNotRectangleException;

public class PlateauTests {

private ApplicationBusiness business;
	
	@Before
	public void init() {
		business = new ApplicationBusiness();
	}
	
	@Test()
	public void whenValidPlateauIsInicialized() throws InvalidCoordinateException {
		CustomResponseStatus response = business.initPlateau(6, 5);
		ValidationUtils.createdValidation(response);
	}
	
	@Test
	public void whenGetValidPlateauStatus() throws InvalidCoordinateException, PlateauNotFoundException {
		business.initPlateau(6, 5);
		PlateauResponse response = (PlateauResponse) business.getPlateauStatus();
		assertThat(response.getPlateau().getxSize(), equalTo(6));
		assertThat(response.getPlateau().getySize(), equalTo(5));
	}
	
	@Test(expected = NegativeCoordinateException.class)
	public void whenXOrYLessThenZeroInPlateauInicialization() throws InvalidCoordinateException {
		business.initPlateau(6, -5);
		business.initPlateau(-6, 5);
	}
	
	@Test(expected = PlateauNotRectangleException.class)
	public void whenXIsEqualYInPlateauInitialization() throws InvalidCoordinateException {
		business.initPlateau(5, 5);
	}
	
	@Test(expected = PlateauNotFoundException.class)
	public void whenGetStatusWithoutInitializePlateau() throws PlateauNotFoundException {
		business.getPlateauStatus();
	}
    
}
