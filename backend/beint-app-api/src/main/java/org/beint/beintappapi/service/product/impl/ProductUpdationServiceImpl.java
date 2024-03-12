package org.beint.beintappapi.service.product.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beint.beintappapi.converter.mapper.ProductMapper;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.UpdateProductDto;
import org.beint.beintappapi.exceptions.NotFoundException;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.product.ProductUpdationService;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUpdationServiceImpl implements ProductUpdationService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private  final ErrorParser errorParser;

    @Override
    @Transactional
    public ProductDto updateProduct(Long productId, UpdateProductDto updateProductDto) {
        
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(errorParser.get("product.not-found")));

        existingProduct.setDescription(updateProductDto.getDescription());
        existingProduct.setCode(updateProductDto.getCode());
        existingProduct.setName(updateProductDto.getName());
        existingProduct.setType(updateProductDto.getType());
        existingProduct.setBrand(updateProductDto.getBrand());
        existingProduct.setCategory(updateProductDto.getCategory());
        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toDto(updatedProduct);
    }
}