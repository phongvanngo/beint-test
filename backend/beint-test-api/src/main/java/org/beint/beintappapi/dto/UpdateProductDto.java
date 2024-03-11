package org.beint.beintappapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link org.beint.beintappapi.domain.Product}
 */
@Data
public class UpdateProductDto implements Serializable {

    private String code;
    private String name;
    private String brand;
    private String type;
    private String description;
    private String category;


}