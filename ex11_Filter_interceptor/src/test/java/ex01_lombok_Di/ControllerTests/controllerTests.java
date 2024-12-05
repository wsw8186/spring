package ex01_lombok_Di.ControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
@Slf4j
public class controllerTests {
	

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext  wac;

	@BeforeEach
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}

	@Test
	public void t1() throws Exception{
		
		//GET /param/p01		
		mockMvc	.perform(get("/param/p01").param("name","홍길동"))
				.andExpect(status().isOk())
				.andDo(print());
		
	}
	
	@Test
	public void t2() throws Exception{
		
		//POST /param/p02
		mockMvc	.perform(post("/param/p03").param("name","홍길동"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void t3() throws Exception{
		
		//POST /param/p03
		mockMvc	.perform(post("/param/p04").content("{'name':'홍길동'}").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	


}
