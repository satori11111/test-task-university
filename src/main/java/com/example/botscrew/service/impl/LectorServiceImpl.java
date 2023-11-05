package com.example.botscrew.service.impl;

import com.example.botscrew.repository.LectorRepository;
import com.example.botscrew.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;

    @Override
    public String searchByTemplate(String template) {
        return String.join(", ", lectorRepository.findByNameContaining(template));
    }
}
