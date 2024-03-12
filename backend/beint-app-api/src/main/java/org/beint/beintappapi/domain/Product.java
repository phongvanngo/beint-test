package org.beint.beintappapi.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    private String brand;
    private String type;
    private String description;
    private String category;

    @Column(name = "created_at", nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @CreationTimestamp
    private ZonedDateTime updatedAt;

}
