package org.beint.beintappapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beint.beintappapi.converter.mapper.ProductMapper;
import org.beint.beintappapi.domain.Category;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.UpdateProductDto;
import org.beint.beintappapi.exceptions.NotFoundException;
import org.beint.beintappapi.repository.CategoryRepository;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.ProductUpdationService;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
        // Retrieve the existing product from the productRepository
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(errorParser.get("product.not-found")));

        // Update the attributes of the existing product with the values from the updateProductDto
        existingProduct.setDescription(updateProductDto.getDescription());
        existingProduct.setCode(updateProductDto.getCode());
        existingProduct.setName(updateProductDto.getName());
        existingProduct.setType(updateProductDto.getType());
        existingProduct.setBrand(updateProductDto.getBrand());

        // Update the categories of the product
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

        // Save the updated product in the productRepository
        Product updatedProduct = productRepository.save(existingProduct);

        // Map the updated product to a ProductDto and return it
        return productMapper.toDto(updatedProduct);
    }
}