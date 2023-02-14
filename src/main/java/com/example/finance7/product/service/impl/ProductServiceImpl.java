package com.example.finance7.product.service.impl;

import com.example.finance7.product.dto.detail.*;
import com.example.finance7.product.dto.list.ProductResponseDTO;
import com.example.finance7.product.entity.*;
import com.example.finance7.product.repository.ProductRepository;
import com.example.finance7.product.service.ProductService;
import com.example.finance7.product.vo.ProductResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * productId를 이용하여 상세 상품 조회
     * @param productId
     * @return
     */
    @Override
    public ProductDetailResponseDTO detail(Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new NoSuchElementException("존재하지 않는 상품입니다.")
        );

        if (product instanceof Card){
            return CardDetailResponseDTO.builder()
                    .category("CARD")
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .benefits(((Card) product).getBenefits().split("\n"))
                    .annualFee(((Card) product).getAnnualFee())
                    .build();

        } else if (product instanceof Loan) {
            return LoanDetailResponseDTO.builder()
                    .category("LOAN")
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .lowRate(((Loan) product).getLowRate())
                    .highRate(((Loan) product).getHighRate())
                    .bound(((Loan) product).getBound())
                    .qualification(((Loan) product).getQualification())
                    .build();

        } else if (product instanceof Savings) {

            return SavingDetailResponseDTO.builder()
                    .category("SAVINGS")
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .basicRate(((Savings) product).getBasicRate())
                    .primeRate(((Savings) product).getPrimeRate())
                    .qualification(((Savings) product).getQualification())
                    .aboutPrimeRate(((Savings) product).getAboutPrimeRate())
                    .build();

        }else {
            Subscription subscription = (Subscription) product;
            return SubscriptionDetailResponseDTO.builder()
                    .category("SUBSCRIPTION")
                    .productName(product.getProductName())
                    .companyName(product.getCompanyName())
                    .companyImage(product.getCompanyImage())
                    .productURL(product.getProductURL())
                    .highRate(subscription.getHighRate())
                    .aboutRate(subscription.getAboutRate())
                    .bound(subscription.getBound())
                    .purchase(subscription.getPurchase())
                    .qualification(subscription.getQualification())
                    .build();
        }
    }

    /**
     * 추천 상품 조회
     * @param tagString
     * @return
     */
    @Override
    public List<ProductResponseVO> recommendation(String tagString) {
//        List<Product> products = productRepository.findAll();
//        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
//        String[] tags = tagString.split("&");
//        for (Product product : products) {
//            for (String tag : tags) {
//                if (product.getTags().contains(tag)) {
//
//                }
//            }
//        }
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
