package com.freshvend.demo1.domain.entity;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.math.BigDecimal; import java.util.ArrayList; import java.util.List;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="order_item")
public class OrderItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="order_id", nullable=false) private Order order;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="product_id", nullable=false) private Product product;
 @Column(nullable=false) private int quantity;
 @Column(name="unit_price", nullable=false, precision=12, scale=2) private BigDecimal unitPrice;
 @Column(nullable=false, precision=12, scale=2) private BigDecimal subtotal;
 @OneToMany(mappedBy="orderItem") private List<StockReservation> reservations=new ArrayList<>();
}
