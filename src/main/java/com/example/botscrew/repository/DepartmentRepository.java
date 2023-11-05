package com.example.botscrew.repository;

import com.example.botscrew.dto.DepartmentStatistic;
import com.example.botscrew.model.Department;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = """
            SELECT l.name
            FROM Department d
            JOIN FETCH Lector l on d.headOfDepartment.id = l.id
            WHERE d.name = :departmentName""")
    Optional<String> findHeadOfDepartment(String departmentName);

    @Query(value = """
            SELECT new com.example.botscrew.dto.DepartmentStatistic(l.degree, count(1))
            FROM Department d
            JOIN d.lectors l
            WHERE d.name = :departmentName
            GROUP BY l.degree
            """)
    List<DepartmentStatistic> findStatisticOfDepartment(String departmentName);

    @Query("""
            SELECT avg(l.salary) as sum FROM Department d
            JOIN d.lectors l
            WHERE d.name = :departmentName
            """)
    Optional<BigDecimal> getAverageSalaryOfDepartment(String departmentName);

    @Query("""
            SELECT count(*) FROM Department d
            JOIN d.lectors l
            WHERE d.name = :departmentName
            """)
    Long countEmployeesByDepartment(String departmentName);
}
