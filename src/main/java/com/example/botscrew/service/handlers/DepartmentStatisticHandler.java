package com.example.botscrew.service.handlers;

import com.example.botscrew.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DepartmentStatisticHandler implements CommandHandler {
    private final static String PREFIX = "Show ";
    private final static String SUFFIX = " statistics";

    private final DepartmentService departmentService;

    @Override
    public String handleCommand(String command) {
        int startIndex = command.indexOf(PREFIX);
        int endIndex = command.indexOf(SUFFIX);
        String departmentName = command.substring(startIndex + PREFIX.length(), endIndex);
        return departmentService.getDepartmentStatistics(departmentName);
    }

    @Override
    public boolean isApplicable(String command) {
        return command.startsWith(PREFIX) && command.endsWith(SUFFIX);
    }
}
