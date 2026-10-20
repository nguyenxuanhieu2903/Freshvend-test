package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StockReservationRepository extends JpaRepository<StockReservation, Long> {
}
