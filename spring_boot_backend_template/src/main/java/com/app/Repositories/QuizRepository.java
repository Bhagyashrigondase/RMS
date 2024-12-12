package com.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.Entities.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String>{

}
