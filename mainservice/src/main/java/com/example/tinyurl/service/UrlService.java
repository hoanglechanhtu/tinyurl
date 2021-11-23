package com.example.tinyurl.service;

import com.example.tinyurl.domain.Url;
import com.example.tinyurl.dto.UrlCreateDto;

public interface UrlService {
    Url createUrl(UrlCreateDto urlCreateDto);
    String getOriginal(String hash);
}
