package org.beint.beintappapi.web.rest.v1;

import lombok.RequiredArgsConstructor;
import org.beint.beintappapi.dto.CategoryDto;
import org.beint.beintappapi.dto.response.DataResponse;
import org.beint.beintappapi.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    @GetMapping()
    public ResponseEntity<DataResponse<List<CategoryDto>>> get() {
        return new ResponseEntity<>(new DataResponse<>(categoryService.getCategory()), HttpStatus.OK);
    }


}
