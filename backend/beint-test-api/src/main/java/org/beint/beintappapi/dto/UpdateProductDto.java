package org.beint.beintappapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.beint.beintappapi.domain.Product}
 */
@Data
public class UpdateProductDto implements Serializable {
    @NotNull(message = "${code.empty}")
    @NotEmpty(message = "${code.empty}")
    @NotBlank(message = "${code.empty}")
    private String code;

    @NotNull(message = "${category.empty}")
    @NotEmpty(message = "${category.empty}")
    private List<String> categories;

    @NotNull(message = "${name.empty}")
    @NotEmpty(message = "${name.empty}")
    @NotBlank(message = "${name.empty}")
    private String name;

    private String brand;
    private String type;
    private String description;

}