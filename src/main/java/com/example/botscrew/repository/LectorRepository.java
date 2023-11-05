package com.example.botscrew.repository;

import com.example.botscrew.model.Lector;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("""
            SELECT l.name
            FROM Lector l
            WHERE l.name
            LIKE concat('%',:name,'%')
            """)
    List<String> findByNameContaining(String name);
}
