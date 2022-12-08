package com.example.order_system.repository;

import com.example.order_system.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepo extends JpaRepository<InvoiceEntity, Long> {
    Optional<InvoiceEntity> findByOrderId(Long orderId);
}