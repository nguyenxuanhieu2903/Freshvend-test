package com.freshvend.demo1.domain.entity;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDate; import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="restock_history")
public class RestockHistory {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="product_id", nullable=false) private Product product;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="operator_id", nullable=false) private User operator;
 @Column(nullable=false) private int quantity;
 @Column(name="remaining_quantity", nullable=false) private int remainingQuantity;
 @Column(name="expiry_date") private LocalDate expiryDate;
 @Column(name="restocked_at", nullable=false) private LocalDateTime restockedAt;
}
