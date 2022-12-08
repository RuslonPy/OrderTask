package com.example.order_system.repository;

import com.example.order_system.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findTopByCustomerIdOrderByDateDesc(Long customerId);

    List<OrderEntity> findAllByCustomerId(Long id);

    List<OrderEntity> findAllByDate(LocalDate date);

}
