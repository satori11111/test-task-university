package com.example.botscrew.repository;

import static com.example.botscrew.config.SqlFilesPaths.DEPARTMENT_LECTOR_DELETE;
import static com.example.botscrew.config.SqlFilesPaths.DEPARTMENT_LECTOR_INSERT;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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
public class LectorRepositoryTest {
    private static final String VALID_TEMPLATE = "n";
    private static final String INVALID_TEMPLATE = "invalid";
    @Autowired
    private LectorRepository lectorRepository;


    @Test
   void findByNameContaining_validTemplate_returnsListOfStrings() {
        List<String> expected = List.of("Andrii", "Roman");
        Assertions.assertEquals(expected,lectorRepository.findByNameContaining(VALID_TEMPLATE));
    }

    @Test
    void findByNameContaining_inValidTemplate_returnsEmptyList() {
        Assertions.assertEquals(Collections.emptyList(),
                lectorRepository.findByNameContaining(INVALID_TEMPLATE));
    }
}
