package org.beint.beintappapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beint.beintappapi.converter.mapper.ProductMapper;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.response.PageData;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.ProductRetrievalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRetrievalServiceImpl implements ProductRetrievalService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;
    @Override
    public PageData<ProductDto> getProducts(Pageable pageable) {

        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductDto> productDtos = productPage.getContent().stream().map(productMapper::toDto).toList();

        return new PageData<ProductDto>(
                productDtos,
                productPage.getPageable().getPageNumber(),
                productPage.getPageable().getPageSize(),
                productPage.getTotalElements()
        );

    }
}
