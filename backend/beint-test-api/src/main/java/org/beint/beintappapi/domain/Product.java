package org.beint.beintappapi.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY) // Many-To-Many
    @JoinTable(name = "product_category",  // Join table for many-to-many
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;


    private String brand;
    private String type;
    private String description;

    private String categoryId;

}
