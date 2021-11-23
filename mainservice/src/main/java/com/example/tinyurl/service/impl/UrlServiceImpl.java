package com.example.tinyurl.service.impl;

import com.example.tinyurl.Constant;
import com.example.tinyurl.domain.Url;
import com.example.tinyurl.dto.UrlCreateDto;
import com.example.tinyurl.exception.ServiceException;
import com.example.tinyurl.repository.UrlRepository;
import com.example.tinyurl.service.SeqGenerateService;
import com.example.tinyurl.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private SeqGenerateService seqGenerateService;

    @Override
    public Url createUrl(UrlCreateDto urlCreateDto) {
        Url url = Url.builder()
                .userId(urlCreateDto.getUserId())
                .originalUrl(urlCreateDto.getUrl())
                .hash(generateHash(urlCreateDto.getUrl()))
                .build();
        urlRepository.save(url);
        return url;
    }

    @Override
    public String getOriginal(String hash) {
        Url url = urlRepository.findByHash(hash).orElseThrow(() -> new ServiceException("ResourceNotFound"));
        return url.getOriginalUrl();
    }

    private String generateHash(String url) {
        try {
            //MsgDigest is not thread safe
            //Need to create new instance each time
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            String hash = getSubstring(digest);
            while (urlRepository.findByHash(hash).isPresent()) {
                messageDigest.update((url + seqGenerateService.generate()).getBytes(StandardCharsets.UTF_8));
                hash = getSubstring(messageDigest.digest());
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            log.error("Error", e);
            throw new ServiceException("Something went wrong");
        }
    }

    private String getSubstring(byte[] digest) {
        return Base64.getEncoder().encodeToString(digest).substring(0, Constant.HASH_LENGTH);
    }
}
