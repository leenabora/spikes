package com.mongospring.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {


    @Override
    public String getDatabaseName() {
        return "mongotest";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new Mongo("localhost");
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
