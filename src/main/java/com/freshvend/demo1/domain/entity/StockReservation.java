package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="stock_reservation")
public class StockReservation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="order_item_id", nullable=false) private OrderItem orderItem;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="restock_history_id", nullable=false) private RestockHistory restockLot;
 @Column(nullable=false) private int quantity;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=16) private ReservationStatus status;
 @Column(name="created_at", nullable=false) private LocalDateTime createdAt;
 @Column(name="updated_at") private LocalDateTime updatedAt;
}
