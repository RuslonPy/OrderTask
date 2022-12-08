package com.example.order_system.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Table(name = "invoices")
@Entity
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    private Integer amount;
    @NotNull
    private LocalDate issued;
    @NotNull
    private LocalDate due;


    @OneToMany(mappedBy = "invoice")
    private Set<PaymentEntity> payments;

    @Column(name = "ord_id", insertable = false, updatable = false)
    private Long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ord_id")
    private OrderEntity order;
}
