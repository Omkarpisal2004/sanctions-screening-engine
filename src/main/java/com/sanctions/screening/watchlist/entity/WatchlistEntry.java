package com.sanctions.screening.watchlist.entity;

import com.sanctions.screening.watchlist.enums.EntityType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(
        name = "watchlist_entries",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_source_record",
                        columnNames = {"source_id", "source_record_id"}
                )
        }
)
public class WatchlistEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    private WatchlistSource source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_batch_id")
    private WatchlistImportBatch importBatch;

    @Column(name = "source_record_id", length = 150)
    private String sourceRecordId;

    @Column(name = "primary_name", nullable = false, length = 300)
    private String primaryName;

    @Column(name = "normalized_name", nullable = false, length = 300)
    private String normalizedName;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false, length = 30)
    private EntityType entityType;

    @Column(name = "country_code", length = 2)
    private String countryCode;

    @Column(name = "nationality_code", length = 2)
    private String nationalityCode;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected WatchlistEntry() {
    }

    @PrePersist
    protected void onCreate() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now(ZoneOffset.UTC);
    }

    public Long getId() {
        return id;
    }

    public WatchlistSource getSource() {
        return source;
    }

    public void setSource(WatchlistSource source) {
        this.source = source;
    }

    public WatchlistImportBatch getImportBatch() {
        return importBatch;
    }

    public void setImportBatch(WatchlistImportBatch importBatch) {
        this.importBatch = importBatch;
    }

    public String getSourceRecordId() {
        return sourceRecordId;
    }

    public void setSourceRecordId(String sourceRecordId) {
        this.sourceRecordId = sourceRecordId;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}