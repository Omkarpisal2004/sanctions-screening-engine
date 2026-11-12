package com.sanctions.screening.watchlist;

import com.sanctions.screening.watchlist.entity.WatchlistSource;
import com.sanctions.screening.watchlist.repository.WatchlistSourceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class WatchlistSourceRepositoryTest {

    @Autowired
    private WatchlistSourceRepository watchlistSourceRepository;

    @Test
    void shouldSaveAndFindWatchlistSourceBySourceCode() {

        WatchlistSource source = new WatchlistSource();
        source.setSourceName("Office of Foreign Assets Control");
        source.setSourceCode("OFAC");
        source.setSourceUrl("https://ofac.treasury.gov");

        WatchlistSource savedSource =
                watchlistSourceRepository.save(source);

        var foundSource = watchlistSourceRepository
                .findBySourceCode("OFAC");

        assertThat(foundSource).isPresent();
        assertThat(foundSource.get().getId())
                .isEqualTo(savedSource.getId());
        assertThat(foundSource.get().getSourceName())
                .isEqualTo("Office of Foreign Assets Control");
    }
}