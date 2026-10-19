package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.PaymentMethod; import com.freshvend.demo1.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.math.BigDecimal; import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="payment")
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="order_id", nullable=false) private Order order;
 @Column(name="transaction_code", nullable=false, unique=true, length=64) private String transactionCode;
 @Column(nullable=false, precision=12, scale=2) private BigDecimal amount;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=24) private PaymentStatus status;
 @Enumerated(EnumType.STRING) @Column(name="payment_method", nullable=false, length=32) private PaymentMethod paymentMethod;
 @Column(name="processed_at") private LocalDateTime processedAt;
}
