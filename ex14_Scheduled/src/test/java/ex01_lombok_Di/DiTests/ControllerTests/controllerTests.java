package ex01_lombok_Di.DiTests.ControllerTests;

import org.junit.jupiter.api.BeforeEach; // Updated annotation for JUnit 5
import org.junit.jupiter.api.Test; // JUnit 5 test annotation
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; // Import this for `get()`
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.ex01.controller.ParameterTestController;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
@Slf4j
class ControllerTests {

    @Autowired
    private ParameterTestController parameterTestController;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach // Updated to JUnit 5 equivalent
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void t1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/param/p01")) 
               .andExpect(status().isOk())  
               .andDo(print()); 
    }

}
