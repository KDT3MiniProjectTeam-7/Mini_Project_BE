package com.example.finance7.product.service.impl;

import com.example.finance7.product.dto.*;
import com.example.finance7.product.entity.*;
import com.example.finance7.product.repository.ProductRepository;
import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findProductByProductId(Long productId) {

        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        } else {
            return product.get();
        }
    }
    /**
     * productId를 이용하여 상세 상품 조회
     * @param productId
     * @return
     */
    @Override
    public ProductResponseVO selectProductDetail(Long productId) {

        try{
            Product product = productRepository.findById(productId).orElseThrow(
                    ()-> new NoSuchElementException("존재하지 않는 상품입니다.")
            );
            ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
            ProductResponseVO productResponseVO = getProductResponseVO();
            productResponseVO.getResultData().add(productResponseDTO);
            return productResponseVO;
        }catch (NoSuchElementException e){
            ProductResponseVO productResponseVO = new ProductResponseVO();
            productResponseVO.setStatus("failed 상품이 존재하지 않습니다.");
            return productResponseVO;
        }
    }

    private ProductResponseVO getProductResponseVO() {
        ProductResponseVO productResponseVO = new ProductResponseVO();
        productResponseVO.setDataNum(1);
        productResponseVO.setStatus("success");
        return productResponseVO;
    }

    /**
     * 추천 상품 조회
     * @param tagString
     * @return
     */
    @Override
    public List<ProductResponseVO> recommendation(String tagString) {
//        List<Product> products = productRepository.findAll();
//        List<ProductResponseVO> result = new ArrayList<>();
//        String[] tags = tagString.split("&");
//        for (Product product : products) {
//            for (String tag : tags) {
//                if (product.getTags().contains(tag)) {
//                    ProductResponseVO productResponseVO = new ProductResponseDTO().toVo(product);
//                    result.add(productResponseVO);
//                }
//            }
//        }
//        return result;
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
