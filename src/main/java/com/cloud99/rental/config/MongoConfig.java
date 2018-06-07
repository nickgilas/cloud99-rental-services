package com.cloud99.rental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@EnableMongoRepositories(basePackages = "com.cloud99.rental.repo")
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    // TODO - NG - make these configurable 
    @Override
    public MongoClient mongoClient() {
	return new MongoClient("192.168.99.100", 27017);
    }

    @Override
    protected String getDatabaseName() {
	return "rental";
    }

}
