package com.sanctions.screening.watchlist.repository;

import com.sanctions.screening.watchlist.entity.WatchlistImportBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchlistImportBatchRepository
        extends JpaRepository<WatchlistImportBatch, Long> {

    Optional<WatchlistImportBatch> findByBatchReference(String batchReference);

    boolean existsByBatchReference(String batchReference);
}