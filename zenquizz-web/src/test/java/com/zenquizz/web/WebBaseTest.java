package com.zenquizz.web;

import com.zenquizz.WebConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {WebConfig.class, WebTestConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public abstract class WebBaseTest {

    @Autowired protected WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    @Before
    public void setUpZenMLBackOfficeAppBaseTest() {
        this.mvc = webAppContextSetup(this.webApplicationContext).build();
    }

    protected MockMvc activateSecurity() {
        this.mvc = webAppContextSetup(this.webApplicationContext).apply(springSecurity()).build();
        return mvc;
    }
}
