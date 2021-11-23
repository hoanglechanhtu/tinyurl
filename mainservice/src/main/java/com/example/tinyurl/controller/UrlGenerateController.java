package com.example.tinyurl.controller;

import com.example.tinyurl.domain.Url;
import com.example.tinyurl.dto.UrlCreateDto;
import com.example.tinyurl.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;

@Slf4j
@RestController("/api")
public class UrlGenerateController {
    @Autowired
    private UrlService urlService;

    @GetMapping(value = "/{hash}")
    ResponseEntity<Void> get(@PathVariable(value = "hash") String hash) {
        String url = urlService.getOriginal(hash);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @PostMapping(value = "/url")
    public ResponseEntity<Url> generate(@RequestBody UrlCreateDto urlCreateDto) {
        log.info("Url {}", urlCreateDto);
        return ResponseEntity.ok(urlService.createUrl(urlCreateDto));
    }
}
