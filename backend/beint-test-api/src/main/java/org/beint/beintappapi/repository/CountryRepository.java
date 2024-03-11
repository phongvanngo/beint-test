package org.beint.beintappapi.repository;

import org.beint.beintappapi.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    @Async
    CompletableFuture<List<Country>> findDistinctByNameInIgnoreCaseOrderByNameAscIdDesc(@Nullable Collection<String> names, Pageable pageable);
}