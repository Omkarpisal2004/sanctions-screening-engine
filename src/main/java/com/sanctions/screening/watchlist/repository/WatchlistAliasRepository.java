package com.sanctions.screening.watchlist.repository;

import com.sanctions.screening.watchlist.entity.WatchlistAlias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistAliasRepository
        extends JpaRepository<WatchlistAlias, Long> {

    List<WatchlistAlias> findByNormalizedAlias(String normalizedAlias);

    boolean existsByWatchlistEntry_IdAndNormalizedAlias(
            Long watchlistEntryId,
            String normalizedAlias
    );
}