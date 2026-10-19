package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="iot_device")
public class IoTDevice {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="device_code", nullable=false, unique=true, length=80) private String deviceCode;
 @Column(name="device_name", nullable=false, length=160) private String deviceName;
 @Column(name="device_type", nullable=false, length=32) private String deviceType;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=16) private DeviceStatus status;
 @Column(name="last_heartbeat") private LocalDateTime lastHeartbeat;
 @Column(name="created_at", nullable=false) private LocalDateTime createdAt;
}
