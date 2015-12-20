package com.zenquizz.dao.repository;

import com.zenquizz.dao.model.TestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestModelRepository extends MongoRepository<TestModel, String> {

    TestModel findByValue1(String value1);
    List<TestModel> findByValue2(Integer value2);
}