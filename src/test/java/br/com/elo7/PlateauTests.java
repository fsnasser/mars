package br.com.elo7;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.elo7.business.ApplicationBusiness;
import br.com.elo7.entities.PlateauResponse;
import br.com.elo7.entities.ResponseStatus;

public class PlateauTests {

	private ApplicationBusiness business;
	
	@Before
	public void init() {
		business = new ApplicationBusiness();
	}
	
	@Test
	public void whenValidPlateauIsInicialized() {
		ResponseStatus response = business.initPlateau(6L, 5L);
		ValidationUtils.createdValidation(response);
	}
	
	@Test
	public void whenGetValidPlateauStatus() {
		business.initPlateau(6L, 5L);
		PlateauResponse response = (PlateauResponse) business.getPlateauStatus();
		assertThat(response.getPlateau().getxSize(), equalTo(6L));
		assertThat(response.getPlateau().getySize(), equalTo(5L));
	}
	
	@Test
	public void whenXOrYLessThenZeroInPlateauInicialization() {
		ResponseStatus response = business.initPlateau(6L, -5L);
		ValidationUtils.xOrYlessThenZeroValidation(response);
		response = business.initPlateau(-6L, 5L);
		ValidationUtils.xOrYlessThenZeroValidation(response);
	}
	
	@Test
	public void whenXOrYNullInPlateauInitialization() {
		ResponseStatus response = business.initPlateau(null, 5L);
		ValidationUtils.nullXAndYValidation(response);
		response = business.initPlateau(6L, null);
		ValidationUtils.nullXAndYValidation(response);
	}
	
	@Test
	public void whenXIsEqualYInPlateauInitialization() {
		ResponseStatus response = business.initPlateau(5L, 5L);
		ValidationUtils.xEqualsYValidation(response);
	}
	
	@Test
	public void whenGetStatusWithoutInitializePlateau() {
		ResponseStatus response = business.getPlateauStatus();
		ValidationUtils.plateauNotInitializedValidation(response);
	}
    
}
