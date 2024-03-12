package org.beint.beintappapi.service.product.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beint.beintappapi.converter.mapper.ProductMapper;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.response.PageData;
import org.beint.beintappapi.exceptions.NotFoundException;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.product.ProductRetrievalService;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRetrievalServiceImpl implements ProductRetrievalService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;
    private final ErrorParser errorParser;
    @Override
    public PageData<ProductDto> getProducts(Pageable pageable) {

        Pageable queryPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").ascending()
        );

        Page<Product> productPage = productRepository.findAll(queryPageable);

        List<ProductDto> productDtos = productPage.getContent().stream().map(productMapper::toDto).toList();

        return new PageData<ProductDto>(
                productDtos,
                productPage.getPageable().getPageNumber(),
                productPage.getPageable().getPageSize(),
                productPage.getTotalElements()
        );
    }

    @Override
    public ProductDto getProduct(Long id) {
        return
                productMapper.toDto(productRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(errorParser.get("product.not-found"))))
                ;
    }
}
