package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
