package br.com.elo7;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsApplication.class) 
@WebAppConfiguration
public class PlateauTests {
	
	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void whenValidPlateauIsInicialized() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"))
                	.andExpect(status().isCreated())
                	.andExpect(content().contentType(contentType))
                	.andExpect(jsonPath("$.message", is("Created")))
                	.andExpect(jsonPath("$.code", is(201)));
	}
	
	@Test
	public void whenGetValidPlateauStatus() throws Exception {
		whenValidPlateauIsInicialized();
		mockMvc.perform(get("/plateau/status"))
                	.andExpect(status().isOk())
                	.andExpect(content().contentType(contentType))
                	.andExpect(jsonPath("$.plateau.probe_coordinates", is(new ArrayList<String>())));
	}
	
	@Test
	public void whenXOrYLessThenZeroInPlateauInicialization() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "-5"))
                	.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenXIsNullInPlateauInicialization() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("y", "5"))
                	.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenYIsNullInPlateauInicialization() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6"))
                	.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenXIsEqualYInPlateauInitialization() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "5")
				.param("y", "5"))
                	.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenGetStatusWithoutInitializePlateau() throws Exception {
		mockMvc.perform(get("/plateau/status"))
				.andExpect(status().isAccepted());
	}
    
}
