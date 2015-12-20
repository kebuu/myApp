package com.zenquizz.dao.repository;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.zenquizz.dao.DbTestBaseConfiguration;
import com.zenquizz.dao.model.TestModel;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DbTestBaseConfiguration.class})
@ActiveProfiles("test")
public class TestModelRepositoryTest {

    @Autowired private TestModelRepository testModelRepository;
    @Autowired private ApplicationContext applicationContext;

    @Rule public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("test");

    @Test
    @UsingDataSet(locations= "/db-data-json/testModel.json", loadStrategy= LoadStrategyEnum.CLEAN_INSERT)
    public void testFindByValues() {
        TestModel alice = new TestModel("Alice", 1);
        TestModel bob = new TestModel("Bob", 2);
        testModelRepository.save(alice);
        testModelRepository.save(bob);

        assertThat(testModelRepository.findAll()).hasSize(3);
        assertThat(testModelRepository.findByValue1("Alice")).isEqualToComparingFieldByField(alice);
        assertThat(testModelRepository.findByValue2(2)).hasSize(1).contains(bob);
    }
}
