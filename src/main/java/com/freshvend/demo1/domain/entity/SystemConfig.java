package com.freshvend.demo1.domain.entity;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="system_config")
public class SystemConfig {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="config_key", nullable=false, unique=true, length=120) private String configKey;
 @Column(name="config_value", nullable=false, columnDefinition="text") private String configValue;
 @Column(columnDefinition="text") private String description;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="updated_by") private User updatedBy;
 @Column(name="updated_at", nullable=false) private LocalDateTime updatedAt;
}
