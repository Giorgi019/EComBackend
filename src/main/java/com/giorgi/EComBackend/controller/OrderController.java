package com.giorgi.EComBackend.controller;

import com.giorgi.EComBackend.model.Order;
import com.giorgi.EComBackend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Order> createOrder(
            @PathVariable Long userId,
            @Valid @RequestBody Order order
    ) {
        Order savedOrder = orderService.createOrder(userId, order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}