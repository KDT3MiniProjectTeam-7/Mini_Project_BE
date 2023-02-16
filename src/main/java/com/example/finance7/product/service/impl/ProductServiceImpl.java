package com.example.finance7.product.service.impl;

import com.example.finance7.product.dto.*;
import com.example.finance7.product.entity.*;
import com.example.finance7.product.repository.*;
import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponsePagingVO;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CardRepository cardRepository;
    private final LoanRepository loanRepository;
    private final SavingsRepository savingsRepository;
    private final SubscriptionRepository subscriptionRepository;

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
    public ProductResponseVO recommendationProductsList(String tagString) {
        List<Product> products = productRepository.findAll();

        String[] tags = tagString.split("&");
        Map<Long, ProductResponseDTO> deduplication = deduplication(products, tags);
        List<ProductResponseDTO> result = new ArrayList<>(deduplication.values());
        if (result.size() == 0){
            return ProductResponseVO.builder()
                    .dataNum(0)
                    .status("failed 검색결과가 없습니다.")
                    .build();
        } else {
            return ProductResponseVO.builder()
                    .dataNum(result.size())
                    .status("success")
                    .resultData(result)
                    .build();
        }
    }

    /**
     * 맵을 이용하여 중복값 제거하는 메서드
     * @param products
     * @param tags
     * @return
     */
    private Map<Long, ProductResponseDTO> deduplication(List<Product> products, String[] tags) {
        Map<Long, ProductResponseDTO> duplicateResult = new HashMap<>();
        for (Product product : products) {
            for (String tag : tags) {
                if (product.getTags().contains(tag)) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
                    duplicateResult.put(product.getProductId(), productResponseDTO);
                }
            }
        }
        return duplicateResult;
    }

    /**
     * 카테고리별 상품 조회
     * @param pageable
     * @return
     */
    @Override
    public ProductResponseVO categoryList(Pageable pageable, String category) {
        return null;
    }

    /**
     * 카테고리별 검색결과 조회
     * @param pageable
     * @param title
     * @param category
     * @return
     */
    @Override
    public ProductResponsePagingVO categoryAndSearch(Pageable pageable, String title, String category) {

        List<ProductResponseDTO> pagingList = new ArrayList<>();
        int totalPage = 0;
        if (category == null || category.equals("")){
            Page<Product> productList = productRepository.findAllByProductNameContaining(title,pageable);
            totalPage = productList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, productList);
        }else if (category.equals("card")) {
            Page<Product> cardList = cardRepository.findAllByProductNameContaining(title, pageable);
            totalPage = cardList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, cardList);
        }else if (category.equals("loan")) {
            Page<Product> loanList = loanRepository.findAllByProductNameContaining(title, pageable);
            totalPage = loanList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, loanList);
        }else if (category.equals("savings")) {
            Page<Product> savingsList = savingsRepository.findAllByProductNameContaining(title, pageable);
            totalPage = savingsList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, savingsList);
        }else if (category.equals("subscription")) {
            Page<Product> subscriptionList = subscriptionRepository.findAllByProductNameContaining(title, pageable);
            totalPage = subscriptionList.getTotalPages();
            pagingListAddProductResponseDTO(pagingList, subscriptionList);
        }

        return getProductResponsePagingVO(pageable, pagingList, totalPage);
    }

    /**
     * 검색결과가 있냐 없냐를 따져서 success failed 를 판별해주는 메서드 추출
     * @param pageable
     * @param pagingList
     * @param totalPage
     * @return
     */
    private ProductResponsePagingVO getProductResponsePagingVO(Pageable pageable,
                                                               List<ProductResponseDTO> pagingList,
                                                               int totalPage) {
        if(pagingList.size() == 0){
            return ProductResponsePagingVO.builder()
                    .dataNum(pagingList.size())
                    .status("failed 검색결과가 없습니다.")
                    .build();

        }else {
            return ProductResponsePagingVO.builder()
                    .dataNum(pagingList.size())
                    .status("success")
                    .resultData(pagingList)
                    .currentPage(pageable.getPageNumber() + 1)
                    .totalPage(totalPage)
                    .build();
        }
    }

    /**
     * 중복되는 로직 메서드 추출
     * @param pagingList
     * @param productList
     */
    private void pagingListAddProductResponseDTO(List<ProductResponseDTO> pagingList, Page<Product> productList) {
        for (Product product : productList) {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO().toDTO(product);
            pagingList.add(productResponseDTO);
        }
    }

}
