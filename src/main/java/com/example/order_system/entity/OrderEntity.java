package com.example.order_system.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Table(name = "orders")
@Entity
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    private LocalDate date;

    @Column(name = "cust_id", insertable = false, updatable = false)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name="cust_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private Set<DetailEntity> details;

    @OneToOne(mappedBy = "order")
    private InvoiceEntity invoice;



}
