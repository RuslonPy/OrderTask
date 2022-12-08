package com.example.order_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "categorys")
@Entity
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;


}
