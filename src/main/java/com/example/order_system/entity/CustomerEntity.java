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
@Table(name = "customers")
@Entity
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String address;
    @NotNull
    private Integer phone;

    @OneToMany(mappedBy = "customer")
    private Set<OrderEntity> orders;
}
