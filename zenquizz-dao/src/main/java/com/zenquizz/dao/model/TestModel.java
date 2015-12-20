package com.zenquizz.dao.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TestModel {

    @Id
    private String id;

    private String value1;
    private Integer value2;

    public TestModel(String value1, Integer value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}