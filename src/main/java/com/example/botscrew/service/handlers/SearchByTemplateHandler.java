package com.example.botscrew.service.handlers;

import com.example.botscrew.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SearchByTemplateHandler implements CommandHandler {
    private final static String PREFIX = "Global search by ";
    private final LectorService lectorService;

    @Override
    public String handleCommand(String command) {
        return lectorService.searchByTemplate(command.substring(PREFIX.length()));
    }

    @Override
    public boolean isApplicable(String command) {
        return command.startsWith(PREFIX);
    }
}
