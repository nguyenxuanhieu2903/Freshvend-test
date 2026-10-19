package com.freshvend.demo1.repository;
import com.freshvend.demo1.domain.entity.IoTDevice;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IoTDeviceRepository extends JpaRepository<IoTDevice, Long> {
}
