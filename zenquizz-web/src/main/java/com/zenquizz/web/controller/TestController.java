package com.zenquizz.web.controller;

import com.zenquizz.dao.model.TestModel;
import com.zenquizz.dao.repository.TestModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    public static final String TEST_MESSAGE = "Alive !";

    @Autowired private TestModelRepository testModelRepository;

    @RequestMapping
    public String test() {
        return TEST_MESSAGE;
    }

    @RequestMapping("/list")
    public List<TestModel> testList() {
        return testModelRepository.findAll();
    }
}
