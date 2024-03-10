package org.beint.beintappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * DTO for {@link org.beint.beintappapi.domain.Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    Long id;
    String code;
    String name;
    String brand;
    String type;
    String description;
    ZonedDateTime createdAt;
    ZonedDateTime updated_at;
    Set<CategoryDto> categories;
}