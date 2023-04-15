package com.example.pastebin.repository;

import com.example.pastebin.model.Paste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface PasteRepository extends JpaRepository<Paste, String>, JpaSpecificationExecutor<Paste> {
    void deleteAllByDataExpiredIsBefore(Instant now);

    @Query(value = "SELECT * FROM table.public.paste t WHERE t.status = 'PUBLIC' " +
            "ORDER BY t.data_created DESC LIMIT 10;", nativeQuery = true)
    List<Paste> findLastTen();
}
