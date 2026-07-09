package com.giorgi.EComBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "შელვეთის სტატუსი სავალდებულოა (მაგ: PENDING,COMPLETED)")
    private String status;

    @NotNull(message = "საბოლოო თანხა სავალდებულოა")
    private BigDecimal totalAmount;

    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private  User user;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
    }

}
