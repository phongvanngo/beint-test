package org.beint.beintappapi.service.product.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.beint.beintappapi.converter.mapper.ProductMapper;
import org.beint.beintappapi.domain.Category;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.UpdateProductDto;
import org.beint.beintappapi.exceptions.NotFoundException;
import org.beint.beintappapi.repository.CategoryRepository;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.product.ProductUpdationService;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductUpdationServiceImpl implements ProductUpdationService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
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


        if (ObjectUtils.isNotEmpty(updateProductDto.getCategories())) {
            
            List<String> categoryNames = updateProductDto.getCategories();
            List<Category> existingCategories = categoryRepository.findByNameIn(categoryNames);
            List<Category> categories = new ArrayList<>();

            for (String categoryName : categoryNames) {
                Optional<Category> existingCategory = existingCategories.stream()
                        .filter(category -> category.getName().equals(categoryName))
                        .findFirst();

                if (existingCategory.isPresent()) {
                    categories.add(existingCategory.get());
                } else {
                    Category newCategory = Category.builder().name(categoryName).build();
                    newCategory = categoryRepository.save(newCategory);
                    categories.add(newCategory);
                }
            }
            existingProduct.setCategories(new HashSet<>(categories));
        }



        
        Product updatedProduct = productRepository.save(existingProduct);

        
        return productMapper.toDto(updatedProduct);
    }
}