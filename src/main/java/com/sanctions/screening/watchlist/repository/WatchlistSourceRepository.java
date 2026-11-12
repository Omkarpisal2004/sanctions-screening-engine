package com.sanctions.screening.watchlist.repository;

import com.sanctions.screening.watchlist.entity.WatchlistSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchlistSourceRepository
        extends JpaRepository<WatchlistSource, Long> {

    Optional<WatchlistSource> findBySourceCode(String sourceCode);

    boolean existsBySourceCode(String sourceCode);
}