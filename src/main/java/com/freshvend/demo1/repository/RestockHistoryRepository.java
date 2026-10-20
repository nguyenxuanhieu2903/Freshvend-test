package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.RestockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RestockHistoryRepository extends JpaRepository<RestockHistory, Long> {
}
