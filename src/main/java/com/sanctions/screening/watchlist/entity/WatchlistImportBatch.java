package com.sanctions.screening.watchlist.entity;

import com.sanctions.screening.watchlist.enums.ImportBatchStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "watchlist_import_batches")
public class WatchlistImportBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    private WatchlistSource source;

    @Column(name = "batch_reference", nullable = false, unique = true, length = 100)
    private String batchReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ImportBatchStatus status;

    @Column(name = "total_records", nullable = false)
    private int totalRecords = 0;

    @Column(name = "processed_records", nullable = false)
    private int processedRecords = 0;

    @Column(name = "failed_records", nullable = false)
    private int failedRecords = 0;

    @Column(name = "started_at", nullable = false, updatable = false)
    private OffsetDateTime startedAt;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    protected WatchlistImportBatch() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.startedAt == null) {
            this.startedAt = OffsetDateTime.now(ZoneOffset.UTC);
        }
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

    public String getBatchReference() {
        return batchReference;
    }

    public void setBatchReference(String batchReference) {
        this.batchReference = batchReference;
    }

    public ImportBatchStatus getStatus() {
        return status;
    }

    public void setStatus(ImportBatchStatus status) {
        this.status = status;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getProcessedRecords() {
        return processedRecords;
    }

    public void setProcessedRecords(int processedRecords) {
        this.processedRecords = processedRecords;
    }

    public int getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(int failedRecords) {
        this.failedRecords = failedRecords;
    }

    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(OffsetDateTime completedAt) {
        this.completedAt = completedAt;
    }
}