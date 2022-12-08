package com.example.order_system.repository;

import com.example.order_system.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DetailRepo extends JpaRepository<DetailEntity, Long> {

    Optional<DetailEntity> findByOrderId(Long orderId);

    List<DetailEntity> findAllByProductId(Long id);

    List<DetailEntity> findAllByOrderId(Long id);
}
