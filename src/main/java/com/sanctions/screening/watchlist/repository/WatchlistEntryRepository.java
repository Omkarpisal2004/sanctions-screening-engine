package com.sanctions.screening.watchlist.repository;

import com.sanctions.screening.watchlist.entity.WatchlistEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistEntryRepository
        extends JpaRepository<WatchlistEntry, Long> {

    Optional<WatchlistEntry> findBySource_IdAndSourceRecordId(
            Long sourceId,
            String sourceRecordId
    );

    List<WatchlistEntry> findByNormalizedNameAndActiveTrue(
            String normalizedName
    );

    boolean existsBySource_IdAndSourceRecordId(
            Long sourceId,
            String sourceRecordId
    );
}