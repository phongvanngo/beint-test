package org.beint.beintappapi.service;

import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.response.PageData;
import org.springframework.data.domain.Pageable;

public interface ProductRetrievalService {
    PageData<ProductDto> getProducts(Pageable pageable);
}
