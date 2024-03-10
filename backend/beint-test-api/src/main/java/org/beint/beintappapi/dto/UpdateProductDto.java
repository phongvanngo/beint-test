package org.beint.beintappapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.beint.beintappapi.domain.Product}
 */
@Data
public class UpdateProductDto implements Serializable {

    private String code;
    private List<String> categories;
    private String name;
    private String brand;
    private String type;
    private String description;

}