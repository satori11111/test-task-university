package com.example.botscrew.repository;

import static com.example.botscrew.config.SqlFilesPaths.DEPARTMENT_LECTOR_DELETE;
import static com.example.botscrew.config.SqlFilesPaths.DEPARTMENT_LECTOR_INSERT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.botscrew.dto.DepartmentStatistic;
import com.example.botscrew.model.enums.Degree;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {
        DEPARTMENT_LECTOR_INSERT
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {
        DEPARTMENT_LECTOR_DELETE
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryTest {
    private static final String VALID_DEPARTMENT_NAME = "Science";
    private static final String INVALID_DEPARTMENT_NAME = "invalidName";
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void findHeadOfDepartmentByName_validName_ReturnsHeadOfDepartmentName() {
        Optional<String> expected = Optional.of("Roman");
        assertEquals(expected,
                departmentRepository.findHeadOfDepartment(VALID_DEPARTMENT_NAME));
    }

    @Test
    void findHeadOfDepartmentByName_inValidName_ReturnsNull() {
        assertEquals(Optional.empty(),departmentRepository.findHeadOfDepartment(INVALID_DEPARTMENT_NAME));
    }

    @Test
    void findStatistic_validName_ReturnsStatistic() {
        List<DepartmentStatistic> departmentStatistics = List.of(new DepartmentStatistic(Degree.ASSISTANT, 1L), new DepartmentStatistic(Degree.PROFESSOR, 1L));
        assertEquals(departmentStatistics, departmentRepository.findStatisticOfDepartment(VALID_DEPARTMENT_NAME));
    }

    @Test
    void findStatistic_invalidName_ReturnsNull() {
        assertEquals(Collections.emptyList(),departmentRepository.findStatisticOfDepartment(INVALID_DEPARTMENT_NAME));
    }

    @Test
    void getAverageSalaryOfDepartment_invalidName_ReturnsOptionalOfEmpty() {
        assertEquals(Optional.empty(),
                departmentRepository.getAverageSalaryOfDepartment(INVALID_DEPARTMENT_NAME));
    }

    @Test
    void getAverageSalaryOfDepartment_validName_ReturnsOptionalOfBigDecimal() {
        Optional<BigDecimal> expected = Optional.of(BigDecimal.valueOf(1800.00));
        assertEquals(expected,
                departmentRepository.getAverageSalaryOfDepartment(VALID_DEPARTMENT_NAME));
    }

    @Test
    void countEmployeesByDepartment_invalidName_Returns0() {
        assertEquals(0,departmentRepository.countEmployeesByDepartment(INVALID_DEPARTMENT_NAME));
    }


    @Test
    void countEmployeesByDepartment_validName_ReturnsLong() {
        assertEquals(2L,departmentRepository.countEmployeesByDepartment(VALID_DEPARTMENT_NAME));
    }


}
