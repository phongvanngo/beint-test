package org.beint.beintappapi.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.exceptions.NotFoundException;
import org.beint.beintappapi.repository.ProductRepository;
import org.beint.beintappapi.service.product.ProductDeletionService;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeletionServiceImpl implements ProductDeletionService {
    private final ProductRepository productRepository;
    private final ErrorParser errorParser;
    @Override
    public void delete(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(errorParser.get("product.not-found")));
        productRepository.delete(existingProduct);
        return;
    }
}
