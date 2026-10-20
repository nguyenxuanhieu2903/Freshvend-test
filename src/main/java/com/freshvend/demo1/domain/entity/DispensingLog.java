package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.DispensingResult;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="dispensing_log")
public class DispensingLog {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="order_id", nullable=false) private Order order;
 @Column(name="attempt_number", nullable=false) private int attemptNumber;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=16) private DispensingResult result;
 @Column(name="failure_reason", columnDefinition="text") private String failureReason;
 @Column(name="attempted_at", nullable=false) private LocalDateTime attemptedAt;
}
