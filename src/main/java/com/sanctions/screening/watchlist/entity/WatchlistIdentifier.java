package com.sanctions.screening.watchlist.entity;

import com.sanctions.screening.watchlist.enums.IdentifierType;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "watchlist_identifiers")
public class WatchlistIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_entry_id", nullable = false)
    private WatchlistEntry watchlistEntry;

    @Enumerated(EnumType.STRING)
    @Column(name = "identifier_type", nullable = false, length = 50)
    private IdentifierType identifierType;

    @Column(name = "identifier_value", nullable = false, length = 200)
    private String identifierValue;

    @Column(name = "issuing_country", length = 2)
    private String issuingCountry;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected WatchlistIdentifier() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now(ZoneOffset.UTC);
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public WatchlistEntry getWatchlistEntry() {
        return watchlistEntry;
    }

    public void setWatchlistEntry(WatchlistEntry watchlistEntry) {
        this.watchlistEntry = watchlistEntry;
    }

    public IdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(IdentifierType identifierType) {
        this.identifierType = identifierType;
    }

    public String getIdentifierValue() {
        return identifierValue;
    }

    public void setIdentifierValue(String identifierValue) {
        this.identifierValue = identifierValue;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}