package com.example.pastebin.repository;

import com.example.pastebin.model.Paste;
import com.example.pastebin.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PasteRepository extends JpaRepository<Paste, String>, JpaSpecificationExecutor<Paste> {
    void deleteAllByDataExpiredIsBefore(Instant now);

    @Query(value = "SELECT * FROM paste WHERE status = 'PUBLIC' " +
            "ORDER BY data_created DESC LIMIT 10;", nativeQuery = true)
    List<Paste> findLastTen();

    @Modifying
    @Query(value="delete from Paste p where p.dataExpired < now()")
    void deleteAll(Instant now);

    List<Paste> findTop10ByStatusAndDataExpiredIsAfterOrderByDataCreatedDesc(Status status, Instant now);

    Optional<Paste> findByUrlAndDataExpiredIsAfter(String url, Instant now);

    List<Paste> findAllByTitleContainsOrBodyContainsAndStatusAndDataExpiredIsAfter(
            String titleText, String bodyText, Status status, Instant now);
}
