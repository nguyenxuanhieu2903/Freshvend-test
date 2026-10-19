package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
