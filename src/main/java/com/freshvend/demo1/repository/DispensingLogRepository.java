package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.DispensingLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DispensingLogRepository extends JpaRepository<DispensingLog, Long> {
}
