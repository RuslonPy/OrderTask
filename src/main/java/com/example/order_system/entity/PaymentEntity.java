package com.example.order_system.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Table(name = "payments")
@Entity
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotNull
    private Timestamp time;
    @NotNull
    private Integer amount;

    @Column(name = "inv_id", insertable = false, updatable = false)
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name="inv_id")
    private InvoiceEntity invoice;
}
