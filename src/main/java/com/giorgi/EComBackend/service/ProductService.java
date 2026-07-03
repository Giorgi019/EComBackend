package com.giorgi.EComBackend.service;

import com.giorgi.EComBackend.model.Category;
import com.giorgi.EComBackend.model.Product;
import com.giorgi.EComBackend.repository.CategoryRepository;
import com.giorgi.EComBackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {return productRepository.findAll();}

    public Product saveProduct(Product product) {return productRepository.save(product);}

    public Optional<Product> getProductById(Long id) {return productRepository.findById(id);}

    public void deleteProduct(long id) {productRepository.deleteById(id);}

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStockQuantity(updatedProduct.getStockQuantity());

            if (updatedProduct.getCategory() != null && updatedProduct.getCategory().getId() != null) {
                Category fullCategory = categoryRepository.findById(updatedProduct.getCategory().getId()).orElse(null);
                existingProduct.setCategory(fullCategory);
            }
            return productRepository.save(existingProduct);
        }
        return null;
    }
}