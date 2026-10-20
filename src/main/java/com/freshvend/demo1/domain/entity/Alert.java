package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.AlertSeverity; import com.freshvend.demo1.domain.enums.AlertType;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="alert")
public class Alert {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="device_id") private IoTDevice device;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id") private Product product;
 @Enumerated(EnumType.STRING) @Column(name="alert_type", nullable=false, length=32) private AlertType alertType;
 @Column(nullable=false, columnDefinition="text") private String message;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=16) private AlertSeverity severity;
 @Column(name="is_acknowledged", nullable=false) private boolean acknowledged;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="acknowledged_by") private User acknowledgedBy;
 @Column(name="acknowledged_at") private LocalDateTime acknowledgedAt;
 @Column(name="created_at", nullable=false) private LocalDateTime createdAt;
}
