package com.zenquizz.web.controller;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.zenquizz.dao.utils.TestUtils;
import com.zenquizz.web.WebBaseTest;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestControllerTest extends WebBaseTest {

    @Rule public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("test");

    @Test
    @UsingDataSet(locations = "/db-data-json/testController.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
    public void testGetListWithoutSecurity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((content().json(TestUtils.getResourceAsString("json/output/list.json"))));
    }
    @Test
    public void testGetTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(TestController.TEST_MESSAGE));
    }

    @Test
    public void testGetListWithSecurity() throws Exception {
        activateSecurity();

        mvc.perform(MockMvcRequestBuilders.get("/test/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        mvc.perform(MockMvcRequestBuilders.get("/test/list").with(user("test").roles("USER")).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}