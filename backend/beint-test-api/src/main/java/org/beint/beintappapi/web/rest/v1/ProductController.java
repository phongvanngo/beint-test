package org.beint.beintappapi.web.rest.v1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.beint.beintappapi.dto.CreateProductDto;
import org.beint.beintappapi.dto.ProductDto;
import org.beint.beintappapi.dto.UpdateProductDto;
import org.beint.beintappapi.dto.response.DataResponse;
import org.beint.beintappapi.dto.response.PageData;
import org.beint.beintappapi.exceptions.ValidationException;
import org.beint.beintappapi.service.ProductCreationService;
import org.beint.beintappapi.service.ProductRetrievalService;
import org.beint.beintappapi.service.ProductUpdationService;
import org.beint.beintappapi.utils.ErrorParser;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ErrorParser errorParser;

    private final ProductRetrievalService productRetrievalService;
    private final ProductCreationService productCreationService;
    private final ProductUpdationService productUpdationService;

    @GetMapping()
    public ResponseEntity<PageData<ProductDto>> get(Pageable pageable) {
        return new ResponseEntity<>(productRetrievalService.getProducts(pageable), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<DataResponse<ProductDto>> create(@Valid @RequestBody CreateProductDto createProductDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(errorParser.get(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
        };

        return new ResponseEntity<>(new DataResponse<>(productCreationService.createProduct(createProductDto)), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<DataResponse<ProductDto>> create(@PathVariable Long productId, @Valid @RequestBody UpdateProductDto updateProductDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(errorParser.get(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));
        };
        return new ResponseEntity<>(new DataResponse<>(productUpdationService.updateProduct(productId, updateProductDto)), HttpStatus.OK);
    }
}
