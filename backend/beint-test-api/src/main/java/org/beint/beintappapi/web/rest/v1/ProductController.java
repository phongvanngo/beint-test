package org.beint.beintappapi.web.rest.v1;

import lombok.RequiredArgsConstructor;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.response.PageData;
import org.beint.beintappapi.service.ProductRetrievalService;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ErrorParser errorParser;

    private final ProductRetrievalService productRetrievalService;

    @GetMapping("/")
    public ResponseEntity<PageData<ProductDto>> get(Pageable pageable) {
        return new ResponseEntity<>(productRetrievalService.getProducts(pageable), HttpStatus.OK);
    }
}
