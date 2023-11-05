package com.example.botscrew.service.handlers;

import com.example.botscrew.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DepartmentEmployeeCountHandler implements CommandHandler {
    private final static String PREFIX = "Show count of employee for ";
    private final DepartmentService departmentService;

    @Override
    public String handleCommand(String command) {
        return departmentService.countEmployeeNumberForDepartment(command.substring(PREFIX.length()));
    }

    @Override
    public boolean isApplicable(String command) {
        return command.startsWith(PREFIX);
    }
}
