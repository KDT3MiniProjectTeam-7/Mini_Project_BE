package com.example.finance7.product.controller;

import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponsePagingVO;
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
    public ProductResponseVO recommendationProduct(@PathVariable String tagString) {
        return productService.recommendationProductsList(tagString);
    }

    @GetMapping("/items/all")
    public ProductResponseVO groupByCategory(String category, @PageableDefault(size = 5) Pageable pageable) {
        return productService.categoryList(pageable, category);
    }

    @GetMapping("/search")
    public ProductResponsePagingVO searchResultByCategory(String category,
                                                          String title,
                                                          @PageableDefault(size = 5) Pageable pageable) {
        System.out.println("category = " + category);
        System.out.println("pageable.getPageNumber() = " + pageable.getPageNumber());
        System.out.println("title = " + title);

        return productService.categoryAndSearch(pageable, title, category);
    }
}
