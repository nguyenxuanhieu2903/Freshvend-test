package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order, Long> {
}
