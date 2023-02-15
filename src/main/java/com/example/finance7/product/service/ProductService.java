package com.example.finance7.product.service;

import com.example.finance7.product.entity.Product;
import com.example.finance7.product.vo.ProductResponseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {


    Product findProductByProductId(Long productId);

    /**
     * 상세 상품 조회
     * @param productId
     * @return
     */
    public ProductResponseVO selectProductDetail(Long productId);

    /**
     * 추천 상품 조회
     * @param tagString
     * @return
     */
    public List<ProductResponseVO> recommendation(String tagString);

    /**
     * 카테고리별 상품 조회
     * @param pageable
     * @param tagString
     * @return
     */
    public Page<ProductResponseVO> categoryList(Pageable pageable, String tagString);

    /**
     * 카테고리별 검색결과 조회
     * @param pageable
     * @param productName
     * @return
     */
    public Page<ProductResponseVO> categoryAndSearch(Pageable pageable,
                                                     String productName,
                                                     String category);
}
