package com.example.botscrew.service.impl;

import com.example.botscrew.dto.DepartmentStatistic;
import com.example.botscrew.model.enums.Degree;
import com.example.botscrew.repository.DepartmentRepository;
import com.example.botscrew.service.DepartmentService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final static String HEAD_OF_DEPARTMENT_RESPONSE = """
            Head of %s department is %s""";
    private final static String STATISTIC_RESPONSE = """
            Answer: assistans - %s
                    associate professors - %s
                    professors - %s""";
    private final static String AVERAGE_SALARY_RESPONSE = """
            The average salary of %s is %s""";
    private static final int INDEX_OF_ASSISTANT_COUNT = 0;
    private static final int INDEX_OF_ASSOCIATE_PROFESSOR_COUNT = 1;
    private static final int INDEX_OF_PROFESSOR_COUNT = 2;
    private static final String EMPTY_RESULT_RESPONSE =
            "Can't find information for department: %s";

    private final DepartmentRepository departmentRepository;

    @Override
    public String getHeadOfDepartment(String name) {
        Optional<String> headOfDepartment = departmentRepository.findHeadOfDepartment(name);
        if (headOfDepartment.isEmpty()) {
            return String.format(EMPTY_RESULT_RESPONSE, name);
        }
        return String.format(HEAD_OF_DEPARTMENT_RESPONSE, name, headOfDepartment.get());
    }

    @Override
    public String getDepartmentStatistics(String name) {
        List<DepartmentStatistic> departmentStatistics = departmentRepository.findStatisticOfDepartment(name);
        List<Long> counts = new ArrayList<>();
        for (Degree degree : Degree.values()) {
            Long count = departmentStatistics.stream()
                    .filter(s -> s.degree() == degree)
                    .findFirst()
                    .orElse(new DepartmentStatistic(degree, 0L)).count();
            counts.add(count);
        }
        return String.format(STATISTIC_RESPONSE, counts.get(INDEX_OF_ASSISTANT_COUNT),
                counts.get(INDEX_OF_ASSOCIATE_PROFESSOR_COUNT),
                counts.get(INDEX_OF_PROFESSOR_COUNT));
    }

    @Override
    public String calculateAverageSalaryOfDepartment(String name) {
        Optional<BigDecimal> averageSalary = departmentRepository.getAverageSalaryOfDepartment(name);
        if (averageSalary.isEmpty()) {
            return String.format(EMPTY_RESULT_RESPONSE, name);
        }
        return String.format(AVERAGE_SALARY_RESPONSE, name, averageSalary.get());
    }

    @Override
    public String countEmployeeNumberForDepartment(String name) {
       return departmentRepository.countEmployeesByDepartment(name).toString();
    }

}
