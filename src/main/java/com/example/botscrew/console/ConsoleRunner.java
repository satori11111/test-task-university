package com.example.botscrew.console;

import com.example.botscrew.service.CommandStrategy;
import com.example.botscrew.service.handlers.CommandHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsoleRunner implements CommandLineRunner {
    private final CommandStrategy commandStrategy;
    @Override
    public void run(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            CommandHandler handler = commandStrategy.getHandler(line);
            System.out.println(handler.handleCommand(line));
        }

    }
}
