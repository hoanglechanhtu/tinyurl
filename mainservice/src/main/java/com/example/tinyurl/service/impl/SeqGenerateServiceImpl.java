package com.example.tinyurl.service.impl;

import com.example.tinyurl.service.SeqGenerateService;
import org.springframework.stereotype.Service;

@Service
public class SeqGenerateServiceImpl implements SeqGenerateService {
    private int seq = 0;
    @Override
    public int generate() {
        return seq++;
    }
}
