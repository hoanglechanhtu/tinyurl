package com.example.tinyurl;

import com.example.tinyurl.domain.Url;
import com.example.tinyurl.domain.User;
import com.example.tinyurl.repository.UrlRepository;
import com.example.tinyurl.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class TinyurlApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UrlRepository urlRepository;
	public static void main(String[] args) {
		SpringApplication.run(TinyurlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
