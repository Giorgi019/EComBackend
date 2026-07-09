package com.giorgi.EComBackend.service;

import com.giorgi.EComBackend.model.Order;
import com.giorgi.EComBackend.model.User;
import com.giorgi.EComBackend.repository.OrderRepository;
import com.giorgi.EComBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Long userId,Order order) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            order.setUser(userOptional.get());
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("მომხმარებელი ID0-ით" + userId + "არ მოიძებნა");
        }
    }
}
