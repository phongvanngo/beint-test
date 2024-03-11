package org.beint.beintappapi.service.product.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beint.beintappapi.converter.mapper.ProductMapper;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.CreateProductDto;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.product.ProductCreationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreationServiceImpl implements ProductCreationService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductDto createProduct(CreateProductDto createProductDto) {

        Product product = Product.builder()
                .description(createProductDto.getDescription())
                .code(createProductDto.getCode())
                .name(createProductDto.getName())
                .type(createProductDto.getType())
                .brand(createProductDto.getBrand())
                .category(createProductDto.getCategory())
                .build();

        return productMapper.toDto(productRepository.save(product));
    }
}