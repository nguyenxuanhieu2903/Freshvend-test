package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
