package org.beint.beintappapi.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "created_at", nullable = false)
    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Set<Product> products = new LinkedHashSet<>();

}