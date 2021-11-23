package com.example.tinyurl.domain;

import lombok.Builder;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Builder
@ToString
public class User {
    @Id
    private String id;
    private String name;
    private String email;

}
