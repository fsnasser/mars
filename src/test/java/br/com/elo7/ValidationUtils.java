package br.com.elo7;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.springframework.http.HttpStatus;

import br.com.elo7.entities.ResponseStatus;

public class ValidationUtils {
	
	public static void createdValidation(ResponseStatus response) {
		createdAssert(response);
	}

	public static void xOrYlessThenZeroValidation(ResponseStatus response) {
		badRequestAssert(response, "Parâmetros 'x' e 'y' não podem ser menores do que zero");
	}
	
	public static void nullXAndYValidation(ResponseStatus response) {
		badRequestAssert(response, "Parâmetro 'x' ou 'y' não enviado");
	}
	
	public static void xEqualsYValidation(ResponseStatus response) {
		badRequestAssert(response, "Parâmetro 'x' precisa ser diferente de 'y'");
	}
	
	public static void plateauNotInitializedValidation(ResponseStatus response) {
		acceptAssert(response, "Necessário iniciar o planalto");		
	}
	
	private static void createdAssert(ResponseStatus response) {
		assertThat(response.getType(), equalTo(HttpStatus.CREATED));
		assertThat(response.getCode(), equalTo(HttpStatus.CREATED.value()));
		assertThat(response.getMessage(), equalTo(HttpStatus.CREATED.getReasonPhrase()));
		assertThat(response.getError(), nullValue());
	}
	
	private static void acceptAssert(ResponseStatus response, String message) {
		assertThat(response.getMessage(), equalTo(message));
		assertThat(response.getType(), equalTo(HttpStatus.ACCEPTED));
		assertThat(response.getCode(), equalTo(HttpStatus.ACCEPTED.value()));
		assertThat(response.getError(), nullValue());
	}
	
	private static void badRequestAssert(ResponseStatus response, String message) {
		assertThat(response.getMessage(), equalTo(message));
		assertThat(response.getType(), equalTo(HttpStatus.BAD_REQUEST));
		assertThat(response.getCode(), equalTo(HttpStatus.BAD_REQUEST.value()));
		assertThat(response.getError(), equalTo(HttpStatus.BAD_REQUEST.getReasonPhrase()));
	}
	
}
