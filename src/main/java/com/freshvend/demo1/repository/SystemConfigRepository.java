package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {
}
