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
import java.util.List;

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
public class ProbeTests {

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
	public void whenValidProbeIsLanded() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "3")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"))
			.andExpect(status().isCreated())
        	.andExpect(content().contentType(contentType))
        	.andExpect(jsonPath("$.message", is("Created")))
        	.andExpect(jsonPath("$.code", is(201)));
	}
	
	@Test
	public void whenGetValidPlateauStatusWithProbe() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "3")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"));
		mockMvc.perform(post("/probe/init")
				.param("x", "3")
				.param("y", "5")
				.param("direction", "S")
				.param("mSequence", "LRMLLMMMMR"));
		List<String> restultData = new ArrayList<String>();
		restultData.add("1 3 N");
		restultData.add("3 5 S");
		mockMvc.perform(get("/plateau/status"))
				.andExpect(status().isOk())
            	.andExpect(content().contentType(contentType))
            	.andExpect(jsonPath("$.plateau.probe_coordinates", is(restultData)));
	}
	
	@Test
	public void whenMoveProbe() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "3")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"));
		mockMvc.perform(get("/probe/move"))
				.andExpect(status().isOk())
		    	.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void whenGetStatusAfterMoveProbe() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "2")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"));
		mockMvc.perform(post("/probe/init")
				.param("x", "3")
				.param("y", "3")
				.param("direction", "E")
				.param("mSequence", "MMRMMRMRRM"));
		mockMvc.perform(get("/probe/move"));
		List<String> restultData = new ArrayList<String>();
		restultData.add("1 3 N");
		restultData.add("5 1 E");
		mockMvc.perform(get("/plateau/status"))
				.andExpect(status().isOk())
		    	.andExpect(content().contentType(contentType))
		    	.andExpect(jsonPath("$.plateau.probe_coordinates", is(restultData)));
	}
	
	@Test
	public void whenXOrYLessThenZeroInProbeInicialization() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "-1")
				.param("y", "2")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenInvalidSequenceInProbeInicialization() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "-1")
				.param("y", "2")
				.param("direction", "N")
				.param("mSequence", "LMLMLELTM"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenProbeDirectionInvalid() throws Exception  {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "2")
				.param("direction", "R")
				.param("mSequence", "MMMMMMM"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenNullXInProbeInicialization() throws Exception  {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("y", "2")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenNullYInProbeInicialization() throws Exception  {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenNullDirectionInProbeInicialization() throws Exception  {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "2")
				.param("mSequence", "LMLMLMLMM"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenNullSequenceInProbeInicialization() throws Exception  {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "2")
				.param("direction", "N"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenProbeInitializedOutPlateau() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "6")
				.param("direction", "N")
				.param("mSequence", "LMLMLMLMM"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenProbeMoveOutPlateau() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(post("/probe/init")
				.param("x", "1")
				.param("y", "2")
				.param("direction", "N")
				.param("mSequence", "MMMMMMM"));
		mockMvc.perform(get("/probe/move"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenMoveProbeNotInitialized() throws Exception {
		mockMvc.perform(post("/plateau/init")
				.param("x", "6")
				.param("y", "5"));
		mockMvc.perform(get("/probe/move"))
				.andExpect(status().isAccepted());
	}
	
}
