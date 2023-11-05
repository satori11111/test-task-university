package com.example.botscrew.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.botscrew.dto.DepartmentStatistic;
import com.example.botscrew.model.enums.Degree;
import com.example.botscrew.repository.DepartmentRepository;
import com.example.botscrew.service.impl.DepartmentServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Mock
    DepartmentRepository departmentRepository;

    @Test
    void getHeadOfDepartment_validDepartmentName_ReturnsString() {
        when(departmentRepository.findHeadOfDepartment(anyString()))
                .thenReturn(Optional.of("Roman"));
        String expected = "Head of Physic department is Roman";
        assertEquals(expected, departmentService.getHeadOfDepartment("Physic"));
        verify(departmentRepository).findHeadOfDepartment(anyString());
    }

    @Test
    void getHeadOfDepartment_invalidDepartmentName_ReturnsString() {
        when(departmentRepository.findHeadOfDepartment(anyString()))
                .thenReturn(Optional.empty());
        assertEquals("Can't find information for department: Math",
                departmentService.getHeadOfDepartment("Math"));
        verify(departmentRepository).findHeadOfDepartment(anyString());
    }

    @Test
    void getDepartmentStatistics_validDepartmentName_ReturnsString() {
        String expected = """
                Answer: assistans - 2
                        associate professors - 0
                        professors - 0""";
        when(departmentRepository.findStatisticOfDepartment(anyString()))
                .thenReturn(List.of(new DepartmentStatistic(Degree.ASSISTANT,2L)));
        assertEquals(expected,departmentService.getDepartmentStatistics("Physic"));
        verify(departmentRepository).findStatisticOfDepartment(anyString());
    }

    @Test
    void getDepartmentStatistics_invalidDepartmentName_ReturnsString() {
        when(departmentRepository.findStatisticOfDepartment(anyString()))
                .thenReturn(List.of());
        String expected = """
                Answer: assistans - 0
                        associate professors - 0
                        professors - 0""";
        assertEquals(expected,departmentService.getDepartmentStatistics("Physic"));
        verify(departmentRepository).findStatisticOfDepartment(anyString());
    }

    @Test
    void calculateAverageSalaryOfDepartment_validName_returnsString() {
        when(departmentRepository.getAverageSalaryOfDepartment(anyString()))
                .thenReturn(Optional.of(BigDecimal.valueOf(1500L)));
        String expected = "The average salary of Physic is 1500";
        assertEquals(expected, departmentService.calculateAverageSalaryOfDepartment("Physic"));
        verify(departmentRepository).getAverageSalaryOfDepartment(anyString());
    }

    @Test
    void calculateAverageSalaryOfDepartment_invalidName_returnsString() {
        when(departmentRepository.getAverageSalaryOfDepartment(anyString()))
                .thenReturn(Optional.empty());
        String expected = "Can't find information for department: Math";
        assertEquals(expected, departmentService.calculateAverageSalaryOfDepartment("Math"));
        verify(departmentRepository).getAverageSalaryOfDepartment(anyString());

    }

    @Test
    void countEmployeeNumberForDepartment_validName_returnsString() {
        when(departmentRepository.countEmployeesByDepartment(anyString()))
                .thenReturn(10L);
         assertEquals("10", departmentService.countEmployeeNumberForDepartment("name"));
         verify(departmentRepository).countEmployeesByDepartment(anyString());
    }
    @Test
    void countEmployeeNumberForDepartment_invalidName_returnsString() {
        when(departmentRepository.countEmployeesByDepartment(anyString()))
                .thenReturn(0L);
        assertEquals("0", departmentService.countEmployeeNumberForDepartment("name"));
        verify(departmentRepository).countEmployeesByDepartment(anyString());
    }
}
