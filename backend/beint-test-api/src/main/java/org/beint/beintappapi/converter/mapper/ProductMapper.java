package org.beint.beintappapi.converter.mapper;

import org.beint.beintappapi.domain.Product;
import org.beint.beintappapi.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
}
