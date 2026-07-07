package com.giorgi.EComBackend.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "პროდუქტის სახელი არ უნდა იყოს ცარიელი")
    private String name;

    @NotBlank(message = "აღწერა სავალდებულოა")
    private String description;

    @NotNull(message = "ფასი სავალდებულოა")
    private BigDecimal price;

    @Min(value = 0, message = "მარაგი არ შეიძლება იყოს უარყოფითი")
    private int stockQuantity;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
