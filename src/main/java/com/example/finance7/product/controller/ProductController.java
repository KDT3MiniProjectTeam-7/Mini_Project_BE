package com.example.finance7.product.controller;

import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/items/{productId}")
    public ProductResponseVO productDetail(@PathVariable Long productId) {
        return productService.selectProductDetail(productId);
    }

    @GetMapping("/Recommendation/{tagString}")
    public List<ProductResponseVO> recommendationProduct(@PathVariable String tagString) {
        return productService.recommendation(tagString);
    }

    @GetMapping("/items/all/{category}/{page}/{tagString}")
    public Page<ProductResponseVO> groupByCategory(@PathVariable String category,
                                                   @PathVariable Integer page,
                                                   @PathVariable String tagString,
                                                   @PageableDefault(page = 1,size = 5) Pageable pageable) {

        return productService.categoryList(pageable, tagString);
    }

    @GetMapping("search/{productName}/{category}/{page}")
    public Page<ProductResponseVO> searchResultByCategory(@PathVariable String productName,
                                                          @PathVariable String category,
                                                          @PathVariable Integer page,
                                                          @PageableDefault(page = 1, size = 5) Pageable pageable) {

        return productService.categoryAndSearch(pageable, productName, category);
    }
}
