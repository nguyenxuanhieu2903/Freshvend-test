package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
