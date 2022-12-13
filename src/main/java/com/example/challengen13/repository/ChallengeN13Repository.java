package com.example.challengen13.repository;

import com.example.challengen13.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeN13Repository extends MongoRepository<UserInfo,String> {
}
