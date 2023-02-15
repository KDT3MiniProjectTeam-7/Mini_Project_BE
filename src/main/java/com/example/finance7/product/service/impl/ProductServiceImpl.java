package com.example.finance7.product.service.impl;

import com.example.finance7.product.entity.Product;
import com.example.finance7.product.repository.ProductRepository;
import com.example.finance7.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findProductByProductId(Long productId) {

        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new NullPointerException("상품을 찾을 수 없습니다.");
        } else {
            return product.get();
        }
    }
}
