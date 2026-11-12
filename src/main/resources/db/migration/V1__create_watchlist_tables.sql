-- =====================================================
-- 1. WATCHLIST SOURCES
-- =====================================================

CREATE TABLE watchlist_sources (
                                   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                   source_name VARCHAR(150) NOT NULL,
                                   source_code VARCHAR(50) NOT NULL UNIQUE,
                                   source_url TEXT,

                                   is_active BOOLEAN NOT NULL DEFAULT TRUE,

                                   created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- =====================================================
-- 2. WATCHLIST IMPORT BATCHES
-- =====================================================

CREATE TABLE watchlist_import_batches (
                                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                          source_id BIGINT NOT NULL,

                                          batch_reference VARCHAR(100) NOT NULL UNIQUE,

                                          status VARCHAR(20) NOT NULL,

                                          total_records INTEGER NOT NULL DEFAULT 0,
                                          processed_records INTEGER NOT NULL DEFAULT 0,
                                          failed_records INTEGER NOT NULL DEFAULT 0,

                                          started_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          completed_at TIMESTAMPTZ,

                                          CONSTRAINT fk_import_batch_source
                                              FOREIGN KEY (source_id)
                                                  REFERENCES watchlist_sources(id),

                                          CONSTRAINT chk_import_batch_status
                                              CHECK (status IN ('PROCESSING', 'COMPLETED', 'FAILED')),

                                          CONSTRAINT chk_import_batch_counts
                                              CHECK (
                                                  total_records >= 0
                                                      AND processed_records >= 0
                                                      AND failed_records >= 0
                                                  )
);


-- =====================================================
-- 3. WATCHLIST ENTRIES
-- =====================================================

CREATE TABLE watchlist_entries (
                                   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                   source_id BIGINT NOT NULL,
                                   import_batch_id BIGINT,

                                   source_record_id VARCHAR(150),

                                   primary_name VARCHAR(300) NOT NULL,
                                   normalized_name VARCHAR(300) NOT NULL,

                                   entity_type VARCHAR(30) NOT NULL,

                                   country_code VARCHAR(2),
                                   nationality_code VARCHAR(2),
                                   date_of_birth DATE,

                                   remarks TEXT,

                                   is_active BOOLEAN NOT NULL DEFAULT TRUE,

                                   created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                   CONSTRAINT fk_entry_source
                                       FOREIGN KEY (source_id)
                                           REFERENCES watchlist_sources(id),

                                   CONSTRAINT fk_entry_import_batch
                                       FOREIGN KEY (import_batch_id)
                                           REFERENCES watchlist_import_batches(id),

                                   CONSTRAINT uq_source_record
                                       UNIQUE (source_id, source_record_id),

                                   CONSTRAINT chk_entity_type
                                       CHECK (
                                           entity_type IN (
                                                           'INDIVIDUAL',
                                                           'ORGANIZATION',
                                                           'VESSEL',
                                                           'AIRCRAFT',
                                                           'OTHER'
                                               )
                                           )
);


-- =====================================================
-- 4. WATCHLIST ALIASES
-- =====================================================

CREATE TABLE watchlist_aliases (
                                   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                   watchlist_entry_id BIGINT NOT NULL,

                                   alias_name VARCHAR(300) NOT NULL,
                                   normalized_alias VARCHAR(300) NOT NULL,

                                   alias_type VARCHAR(50),

                                   created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                   CONSTRAINT fk_alias_entry
                                       FOREIGN KEY (watchlist_entry_id)
                                           REFERENCES watchlist_entries(id)
                                           ON DELETE CASCADE,

                                   CONSTRAINT uq_entry_alias
                                       UNIQUE (watchlist_entry_id, normalized_alias)
);


-- =====================================================
-- 5. WATCHLIST IDENTIFIERS
-- =====================================================

CREATE TABLE watchlist_identifiers (
                                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

                                       watchlist_entry_id BIGINT NOT NULL,

                                       identifier_type VARCHAR(50) NOT NULL,
                                       identifier_value VARCHAR(200) NOT NULL,

                                       issuing_country VARCHAR(2),

                                       created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                       CONSTRAINT fk_identifier_entry
                                           FOREIGN KEY (watchlist_entry_id)
                                               REFERENCES watchlist_entries(id)
                                               ON DELETE CASCADE
);


-- =====================================================
-- INDEXES
-- =====================================================

CREATE INDEX idx_watchlist_entries_source
    ON watchlist_entries(source_id);

CREATE INDEX idx_watchlist_entries_normalized_name
    ON watchlist_entries(normalized_name);

CREATE INDEX idx_watchlist_entries_active
    ON watchlist_entries(is_active);

CREATE INDEX idx_watchlist_aliases_normalized
    ON watchlist_aliases(normalized_alias);

CREATE INDEX idx_watchlist_identifiers_value
    ON watchlist_identifiers(identifier_value);

CREATE INDEX idx_import_batches_source
    ON watchlist_import_batches(source_id);