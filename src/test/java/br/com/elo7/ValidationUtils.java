package br.com.elo7;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.springframework.http.HttpStatus;

import br.com.elo7.entities.CustomResponseStatus;

public class ValidationUtils {
	
	public static void OKValidation(CustomResponseStatus response) {
		OKAssert(response);
	}
	
	public static void ContentValidation(CustomResponseStatus response) {
		contentAssert(response);
	}
	
	public static void createdValidation(CustomResponseStatus response) {
		createdAssert(response);
	}
	
	private static void contentAssert(CustomResponseStatus response) {
		assertThat(response.getType(), equalTo(HttpStatus.OK));
		assertThat(response.getCode(), nullValue());
		assertThat(response.getMessage(), nullValue());
		assertThat(response.getError(), nullValue());
	}
	
	private static void OKAssert(CustomResponseStatus response) {
		assertThat(response.getType(), equalTo(HttpStatus.OK));
		assertThat(response.getCode(), equalTo(HttpStatus.OK.value()));
		assertThat(response.getMessage(), equalTo(HttpStatus.OK.getReasonPhrase()));
		assertThat(response.getError(), nullValue());
	}
	
	private static void createdAssert(CustomResponseStatus response) {
		assertThat(response.getType(), equalTo(HttpStatus.CREATED));
		assertThat(response.getCode(), equalTo(HttpStatus.CREATED.value()));
		assertThat(response.getMessage(), equalTo(HttpStatus.CREATED.getReasonPhrase()));
		assertThat(response.getError(), nullValue());
	}
	
}
