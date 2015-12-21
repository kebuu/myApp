package com.zenquizz.web.controller;

import com.zenquizz.dao.model.TestModel;
import com.zenquizz.dao.repository.TestModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired TestModelRepository testModelRepository;

    @RequestMapping("/test")
    public String test() {
        return "Alive !";
    }

    @RequestMapping("/test/list")
    public List<TestModel> testList() {
        return testModelRepository.findAll();
    }
}
