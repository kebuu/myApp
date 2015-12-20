package com.zenquizz.dao;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.zenquizz.dao.base.ConstantKt;
import org.jongo.Jongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public Jongo jongo(Mongo mongo) {
        DB db = mongo.getDB(ConstantKt.MONGO_DB_NAME);
        return new Jongo(db);
    }
}