package com.sanctions.screening.watchlist.repository;

import com.sanctions.screening.watchlist.entity.WatchlistIdentifier;
import com.sanctions.screening.watchlist.enums.IdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistIdentifierRepository
        extends JpaRepository<WatchlistIdentifier, Long> {

    List<WatchlistIdentifier> findByIdentifierValue(String identifierValue);

    List<WatchlistIdentifier> findByWatchlistEntry_Id(Long watchlistEntryId);

    boolean existsByIdentifierTypeAndIdentifierValue(
            IdentifierType identifierType,
            String identifierValue
    );
}