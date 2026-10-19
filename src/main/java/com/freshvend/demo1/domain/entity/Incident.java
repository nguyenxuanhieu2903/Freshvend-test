package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.IncidentStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="incident")
public class Incident {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="reported_by") private User reportedBy;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="resolved_by") private User resolvedBy;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="alert_id") private Alert linkedAlert;
 @Column(nullable=false, length=180) private String title;
 @Column(columnDefinition="text") private String description;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=24) private IncidentStatus status;
 @Column(name="resolution_note", columnDefinition="text") private String resolutionNote;
 @Column(name="resolved_at") private LocalDateTime resolvedAt;
 @Column(name="created_at", nullable=false) private LocalDateTime createdAt;
 @Column(name="updated_at") private LocalDateTime updatedAt;
}
