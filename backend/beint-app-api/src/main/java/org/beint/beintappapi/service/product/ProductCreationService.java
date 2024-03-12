package org.beint.beintappapi.service.product;

import org.beint.beintappapi.dto.CreateProductDto;
import org.beint.beintappapi.dto.ProductDto;

public interface ProductCreationService {
    ProductDto createProduct(CreateProductDto createProductDto);
}
