package com.cloud99.rental.config;

import com.mongodb.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.cloud99.rental.repo")
@Configuration
@PropertySource("classpath:application.properties")
public class MongoConfig extends AbstractMongoConfiguration {

	@Value("${mongo.host}")
	private String hostName;

	@Value("${mongo.port}")
	private Integer port;

    @Override
    public MongoClient mongoClient() {
		return new MongoClient(hostName, port);
    }

    @Override
    protected String getDatabaseName() {
	return "rental";
    }

}
