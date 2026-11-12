package com.sanctions.screening.watchlist.entity;

import com.sanctions.screening.watchlist.enums.AliasType;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(
        name = "watchlist_aliases",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_entry_alias",
                        columnNames = {"watchlist_entry_id", "normalized_alias"}
                )
        }
)
public class WatchlistAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_entry_id", nullable = false)
    private WatchlistEntry watchlistEntry;

    @Column(name = "alias_name", nullable = false, length = 300)
    private String aliasName;

    @Column(name = "normalized_alias", nullable = false, length = 300)
    private String normalizedAlias;

    @Enumerated(EnumType.STRING)
    @Column(name = "alias_type", length = 50)
    private AliasType aliasType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    protected WatchlistAlias() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now(ZoneOffset.UTC);
    }

    public Long getId() {
        return id;
    }

    public WatchlistEntry getWatchlistEntry() {
        return watchlistEntry;
    }

    public void setWatchlistEntry(WatchlistEntry watchlistEntry) {
        this.watchlistEntry = watchlistEntry;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getNormalizedAlias() {
        return normalizedAlias;
    }

    public void setNormalizedAlias(String normalizedAlias) {
        this.normalizedAlias = normalizedAlias;
    }

    public AliasType getAliasType() {
        return aliasType;
    }

    public void setAliasType(AliasType aliasType) {
        this.aliasType = aliasType;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}