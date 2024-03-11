package org.beint.beintappapi.service.category.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beint.beintappapi.converter.mapper.CategoryMapper;
import org.beint.beintappapi.dto.CategoryDto;
import org.beint.beintappapi.repository.CategoryRepository;
import org.beint.beintappapi.service.category.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getCategory() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }
}
