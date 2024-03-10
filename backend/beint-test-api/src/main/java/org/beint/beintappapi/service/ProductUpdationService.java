package org.beint.beintappapi.service;

import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.UpdateProductDto;
import org.springframework.stereotype.Service;

public interface ProductUpdationService {
    ProductDto updateProduct(Long productId, UpdateProductDto updateProductDto);
}
