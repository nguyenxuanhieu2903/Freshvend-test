package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
