package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
