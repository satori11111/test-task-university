package com.example.botscrew.service;

import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    String getHeadOfDepartment(String name);

    String getDepartmentStatistics(String name);

    String calculateAverageSalaryOfDepartment(String name);

    String countEmployeeNumberForDepartment(String name);
}
