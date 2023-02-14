package com.example.finance7.product.service.impl;

import com.example.finance7.product.dto.detail.ProductDetailResponseDTO;
import com.example.finance7.product.repository.ProductRepository;
import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * 상세 상품 조회
     * @param productId
     * @return
     */
    @Override
    public ProductDetailResponseDTO detail(Long productId) {

        return null;
    }

    /**
     * 추천 상품 조회
     * @param tagString
     * @return
     */
    @Override
    public List<ProductResponseVO> recommendation(String tagString) {
        return null;
    }

    /**
     * 카테고리별 상품 조회
     * @param pageable
     * @param tagString
     * @return
     */
    @Override
    public Page<ProductResponseVO> categoryList(Pageable pageable, String tagString) {
        return null;
    }

    /**
     * 카테고리별 검색결과 조회
     * @param pageable
     * @param productName
     * @param category
     * @return
     */
    @Override
    public Page<ProductResponseVO> categoryAndSearch(Pageable pageable, String productName, String category) {
        return null;
    }
}
