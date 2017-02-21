package net.wyun;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import net.wyun.wcrs.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class ApplicationTests {
	
	@Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = webAppContextSetup(wac)
                .build();
    }

	@Test
	public void contextLoads() {
	}

}
