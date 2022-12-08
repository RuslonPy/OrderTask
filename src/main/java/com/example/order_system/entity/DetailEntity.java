package com.example.order_system.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "details")
@Entity
@NoArgsConstructor
public class DetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    private Integer quantity;

    @Column(name = "ord_id", insertable = false, updatable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="ord_id")
    private OrderEntity order;

    @Column(name = "pr_id", insertable = false, updatable = false)
    private Long productId;

    @ManyToOne
    @JoinColumn(name="pr_id")
    private ProductEntity product;
}
