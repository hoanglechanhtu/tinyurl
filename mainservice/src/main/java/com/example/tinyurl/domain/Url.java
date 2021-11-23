package com.example.tinyurl.domain;

import com.example.tinyurl.Constant;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Builder
@ToString
@Data
public class Url {
    @Id
    private String id;
    private String hash;
    private String originalUrl;
    @Builder.Default
    private Date creationDate = Date.from(Instant.now());
    @Builder.Default
    private Date expirationDate = Date.from(Instant.now().plus(Constant.EXPIRATION_TIME_DAYS, ChronoUnit.DAYS));
    private String userId;
}
