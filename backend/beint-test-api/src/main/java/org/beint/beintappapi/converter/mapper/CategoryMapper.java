package org.beint.beintappapi.converter.mapper;

import org.beint.beintappapi.domain.Category;
import org.beint.beintappapi.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDto toDto(Category category);
}
