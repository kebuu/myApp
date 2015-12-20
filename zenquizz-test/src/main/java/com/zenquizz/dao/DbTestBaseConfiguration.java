package com.zenquizz.dao;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableAutoConfiguration
public class DbTestBaseConfiguration {

    @Bean
    @Primary
    public Mongo fongo() {
        return new Fongo("dao-test").getMongo();
    }
}
