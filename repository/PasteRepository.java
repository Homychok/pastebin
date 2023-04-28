package com.example.pastebin.repository;

import com.example.pastebin.model.Paste;
import com.example.pastebin.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface PasteRepository extends JpaRepository<Paste, String>, JpaSpecificationExecutor<Paste> {
    Paste findPastById(String id);

    @Modifying
    @Query(value="delete from Paste p where p.dateExpired < now()")
    void deleteAll(Instant now);

    List<Paste> findTop10ByStatusAndDateExpiredIsAfterOrderByDateCreatedDesc(Status status, Instant instant);

    @Query(value = "SELECT p FROM Paste p where p.status = ?1 and p.dateExpired > now() and (p.title = ?2 or p.body = ?3)")
    List<Paste> findAllByTitleOrBody(Status status, String title, String body);
}
