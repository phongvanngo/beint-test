package org.beint.beintappapi.repository;

import org.beint.beintappapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameIn(List<String> names);
}
