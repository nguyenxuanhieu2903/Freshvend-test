package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.DataSourceType; import com.freshvend.demo1.domain.enums.DoorStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.math.BigDecimal; import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="sensor_data")
public class SensorData {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="device_id", nullable=false) private IoTDevice device;
 @Column(precision=6, scale=2) private BigDecimal temperature;
 @Column(precision=6, scale=2) private BigDecimal humidity;
 @Enumerated(EnumType.STRING) @Column(name="door_status", nullable=false, length=16) private DoorStatus doorStatus;
 @Enumerated(EnumType.STRING) @Column(name="data_source", nullable=false, length=16) private DataSourceType dataSource;
 @Column(name="recorded_at", nullable=false) private LocalDateTime recordedAt;
 @Column(name="received_at", nullable=false) private LocalDateTime receivedAt;
}
