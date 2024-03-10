package org.beint.beintappapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beint.beintappapi.converter.mapper.ProductMapper;
import org.beint.beintappapi.domain.Category;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.CreateProductDto;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.repository.CategoryRepository;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.ProductCreationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreationServiceImpl implements ProductCreationService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductDto createProduct(CreateProductDto createProductDto) {

        List<String> categoryNames = createProductDto.getCategories();
        List<Category> existingCategories = categoryRepository.findByNameIn(categoryNames);

        List<Category> categories = categoryNames.stream()
                .map(name -> existingCategories.stream()
                        .filter(category -> category.getName().equals(name))
                        .findFirst()
                        .orElse(Category.builder().name(name).build()))
                .toList();

        Product product = Product.builder()
                .categories(new HashSet<>(categories))
                .description(createProductDto.getDescription())
                .code(createProductDto.getCode())
                .name(createProductDto.getName())
                .type(createProductDto.getType())
                .brand(createProductDto.getBrand())
                .build();

        return productMapper.toDto(productRepository.save(product));
    }
}