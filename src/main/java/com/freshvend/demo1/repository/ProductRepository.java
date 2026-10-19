package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {
}
