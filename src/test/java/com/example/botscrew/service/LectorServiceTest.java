package com.example.botscrew.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.botscrew.repository.LectorRepository;
import com.example.botscrew.service.impl.LectorServiceImpl;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LectorServiceTest {
    @InjectMocks
    LectorServiceImpl lectorService;

    @Mock
    LectorRepository lectorRepository;

    @Test
    void findByNameContaining_validTemplate_returnsString() {
        when(lectorRepository.findByNameContaining(anyString()))
                .thenReturn(List.of("Roman"));
        Assertions.assertEquals("Roman",lectorService.searchByTemplate("R"));
        verify(lectorRepository).findByNameContaining(anyString());
    }

    @Test
    void findByNameContaining_inValidTemplate_returnsEmptyList() {
        when(lectorRepository.findByNameContaining(anyString()))
                .thenReturn(Collections.emptyList());
        Assertions.assertEquals("",lectorService.searchByTemplate("R"));
        verify(lectorRepository).findByNameContaining(anyString());
    }
}
