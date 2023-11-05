package com.example.botscrew.service;

import com.example.botscrew.service.handlers.CommandHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommandStrategy {
    private final List<CommandHandler> commandHandlers;

    public CommandHandler getHandler(String command) {
        return commandHandlers.stream()
                .filter(ph -> ph.isApplicable(command))
                .findFirst()
                .orElseGet(CommandHandler::wrongOperationHandler);
    }
}
