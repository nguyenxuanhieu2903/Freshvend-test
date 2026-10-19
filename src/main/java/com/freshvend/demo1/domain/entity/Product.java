package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.math.BigDecimal; import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name="product")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, length=180) private String name;
 @Column(columnDefinition="text") private String description;
 @Column(name="image_url") private String imageUrl;
 @Column(nullable=false, precision=12, scale=2) private BigDecimal price;
 // Aggregate stock; FIFO lot balances are recorded in RestockHistory.
 @Column(name="stock_quantity", nullable=false) private int stockQuantity;
 @Column(name="low_stock_threshold", nullable=false) private int lowStockThreshold;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=24) private ProductStatus status;
 @Column(name="created_at", nullable=false) private LocalDateTime createdAt;
 @Column(name="updated_at") private LocalDateTime updatedAt;
}
