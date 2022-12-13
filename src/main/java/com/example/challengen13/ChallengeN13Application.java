package com.example.challengen13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ChallengeN13Application {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeN13Application.class, args);
    }

}
