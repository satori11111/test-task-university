package com.example.botscrew.service;

import org.springframework.stereotype.Service;

@Service
public interface LectorService {
    String searchByTemplate(String template);
}
