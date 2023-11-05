package com.example.botscrew.service.impl;

import com.example.botscrew.repository.LectorRepository;
import com.example.botscrew.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LectorServiceImpl implements LectorService {
    private static final String EMPTY_RESULT_RESPONSE =
            "Your search: %s did not return any matches";
    private final LectorRepository lectorRepository;

    @Override
    public String searchByTemplate(String template) {
        String result = String.join(", ", lectorRepository.findByNameContaining(template));
        if (result.isEmpty()) {
            return String.format(EMPTY_RESULT_RESPONSE, template);
        }
        return result;
    }
}
