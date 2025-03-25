package com.example.infrastructure.orderItem;

import com.example.domain.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrderItemJpaRepo extends JpaRepository<OrderItem, UUID> {
}
