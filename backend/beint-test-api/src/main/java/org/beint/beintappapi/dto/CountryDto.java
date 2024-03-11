package org.beint.beintappapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link org.beint.beintappapi.Country}
 */
@Data
public class CountryDto {
    UUID id;
    @NotNull
    @Email
    @NotEmpty
    @NotBlank
    @URL
    String name;
}