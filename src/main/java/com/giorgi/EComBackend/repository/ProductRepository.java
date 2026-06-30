package com.giorgi.EComBackend.repository;

import com.giorgi.EComBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
