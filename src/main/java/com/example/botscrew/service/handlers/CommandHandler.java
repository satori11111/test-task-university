package com.example.botscrew.service.handlers;

public interface CommandHandler {

    String handleCommand(String command);

    boolean isApplicable(String command);

    static CommandHandler wrongOperationHandler() {
        return new CommandHandler() {
            @Override
            public String handleCommand(String command) {
                return """
                        Wrong command, here's list of commands:
                        Who is head of department {department_name}
                        Show {department_name} statistics
                        Show the average salary for the department {department_name}
                        Show count of employee for {department_name}
                        Global search by {template}
                        exit
                        """;
            }

            @Override
            public boolean isApplicable(String command) {
                return false;
            }
        };
    }
}
