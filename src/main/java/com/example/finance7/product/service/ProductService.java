package com.example.finance7.product.service;

import com.example.finance7.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product findProductByProductId(Long productId);
}
