package com.zenquizz.dao;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbTestConfig {

    @Bean
    public MongoClient fongo() {
        return new Fongo("dao-test").getMongo();
    }
}
