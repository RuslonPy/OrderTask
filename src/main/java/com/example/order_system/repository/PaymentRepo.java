package com.example.order_system.repository;

import com.example.order_system.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findAllByInvoiceId(Long invoiceId);

}
