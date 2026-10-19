package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.OrderStatus; import com.freshvend.demo1.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.ArrayList; import java.util.List;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="orders")
public class Order {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="order_code", nullable=false, unique=true, length=40) private String orderCode;
 // Nullable for anonymous customer checkout in the MVP.
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="customer_id") private User customer;
 @Column(name="total_amount", nullable=false, precision=12, scale=2) private BigDecimal totalAmount;
 @Enumerated(EnumType.STRING) @Column(name="order_status", nullable=false, length=32) private OrderStatus orderStatus;
 @Enumerated(EnumType.STRING) @Column(name="payment_status", nullable=false, length=24) private PaymentStatus paymentStatus;
 @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true) private List<OrderItem> items=new ArrayList<>();
 @OneToMany(mappedBy="order") private List<Payment> payments=new ArrayList<>();
 @OneToMany(mappedBy="order") private List<DispensingLog> dispensingLogs=new ArrayList<>();
 @Column(name="created_at", nullable=false) private LocalDateTime createdAt;
 @Column(name="updated_at") private LocalDateTime updatedAt;
}
