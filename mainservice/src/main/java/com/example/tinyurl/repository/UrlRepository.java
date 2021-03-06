package com.example.tinyurl.repository;

import com.example.tinyurl.domain.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url, String> {
    Optional<Url> findByHash(String hash);
}
