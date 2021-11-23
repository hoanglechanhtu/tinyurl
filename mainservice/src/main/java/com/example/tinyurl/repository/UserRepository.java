package com.example.tinyurl.repository;

import com.example.tinyurl.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    List<User> findByName(String name);
}
