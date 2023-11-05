package com.example.botscrew.service.handlers;

public interface CommandHandler {

    String handleCommand(String command);

    boolean isApplicable(String command);

    static CommandHandler wrongOperationHandler() {
        return new CommandHandler() {
            @Override
            public String handleCommand(String command) {
                return "Wrong operation";
            }

            @Override
            public boolean isApplicable(String command) {
                return false;
            }
        };
    }
}
