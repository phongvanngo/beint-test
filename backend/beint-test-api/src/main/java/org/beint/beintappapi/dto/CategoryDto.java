package org.beint.beintappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * DTO for {@link org.beint.beintappapi.domain.Category}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    Long id;
    String name;
    String description;
    ZonedDateTime createdAt;
}