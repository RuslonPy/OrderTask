package com.example.order_system.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "products")
@Entity
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Integer price;
    @NotNull
    private String phone;

    @OneToMany(mappedBy = "product")
    private Set<DetailEntity> details;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;
}
