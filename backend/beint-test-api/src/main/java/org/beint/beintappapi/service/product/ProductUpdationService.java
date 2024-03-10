package org.beint.beintappapi.service.product;

import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.UpdateProductDto;

public interface ProductUpdationService {
    ProductDto updateProduct(Long productId, UpdateProductDto updateProductDto);
}
