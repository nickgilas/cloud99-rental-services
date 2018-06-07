package com.cloud99.rental.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class RentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalServiceApplication.class, args);
	}
}
