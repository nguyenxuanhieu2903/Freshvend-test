package com.freshvend.demo1.domain.entity;
import com.freshvend.demo1.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor
@Entity @Table(name = "users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true, length=80) private String username;
 @Column(name="password_hash", nullable=false) private String passwordHash;
 @Column(name="full_name", nullable=false, length=160) private String fullName;
 @Column(length=180) private String email;
 @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private UserRole role;
 @Column(name="is_active", nullable=false) private boolean active=true;
 @Column(name="created_at", nullable=false) private LocalDateTime createdAt;
 @Column(name="updated_at") private LocalDateTime updatedAt;
}
