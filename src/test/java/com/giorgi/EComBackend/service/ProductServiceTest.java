package com.giorgi.EComBackend.service;

import com.giorgi.EComBackend.model.Product;
import com.giorgi.EComBackend.repository.CategoryRepository;
import com.giorgi.EComBackend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void saveProductShouldSaveProductSuccessfully() {
        Product product = new Product();
        product.setName("Test Phone");

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);

        assertEquals("Test Phone", savedProduct.getName());
    }
}