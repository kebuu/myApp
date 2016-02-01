package com.zenquizz.dao.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;

    private String email;
    private String firstName;
    private String lastName;
}