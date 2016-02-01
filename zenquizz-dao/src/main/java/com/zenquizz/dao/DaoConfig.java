package com.zenquizz.dao;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.zenquizz.base.ConstantKt;
import org.jongo.Jongo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class DaoConfig {

    @Bean
    public Jongo jongo(Mongo mongo) {
        DB db = mongo.getDB(ConstantKt.getMONGO_DB_NAME());
        return new Jongo(db);
    }
}