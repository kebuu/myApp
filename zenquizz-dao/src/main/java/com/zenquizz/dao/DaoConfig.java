package com.zenquizz.dao;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.zenquizz.base.ConstantKt;
import org.jongo.Jongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DaoConfig {

    @Bean
    public Jongo jongo(Mongo mongo) {
        DB db = mongo.getDB(ConstantKt.getMONGO_DB_NAME());
        return new Jongo(db);
    }
}