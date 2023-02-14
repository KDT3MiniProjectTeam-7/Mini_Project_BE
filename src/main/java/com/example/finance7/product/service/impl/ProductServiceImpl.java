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

import java.util.ArrayList;
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
    public ProductResponseVO selectProductDetail(Long productId) {

        try{
            Product product = productRepository.findById(productId).orElseThrow(
                    ()-> new NoSuchElementException("존재하지 않는 상품입니다.")
            );

            if (product instanceof Card){
                CardResponseDTO card = CardResponseDTO.builder()
                        .category("CARD")
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .companyName(product.getCompanyName())
                        .companyImage(product.getCompanyImage())
                        .productURL(product.getProductURL())
                        .benefits(((Card) product).getBenefits().split("\\\\n"))
                        .annualFee(((Card) product).getAnnualFee())
                        .build();
                ProductResponseVO productResponseVO = getProductResponseVO();
                productResponseVO.getResultData().add(card);
                return productResponseVO;

            } else if (product instanceof Loan) {
                LoanResponseDTO loan = LoanResponseDTO.builder()
                        .category("LOAN")
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .companyName(product.getCompanyName())
                        .companyImage(product.getCompanyImage())
                        .productURL(product.getProductURL())
                        .lowRate(((Loan) product).getLowRate())
                        .highRate(((Loan) product).getHighRate())
                        .bound(((Loan) product).getBound().split("\\\\n"))
                        .qualification(((Loan) product).getQualification().split("\\\\n"))
                        .build();
                ProductResponseVO productResponseVO = getProductResponseVO();
                productResponseVO.getResultData().add(loan);
                return productResponseVO;

            } else if (product instanceof Savings) {

                SavingResponseDTO savings = SavingResponseDTO.builder()
                        .category("SAVINGS")
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .companyName(product.getCompanyName())
                        .companyImage(product.getCompanyImage())
                        .productURL(product.getProductURL())
                        .basicRate(((Savings) product).getBasicRate())
                        .primeRate(((Savings) product).getPrimeRate())
                        .qualification(((Savings) product).getQualification().split("\\\\n"))
                        .aboutPrimeRate(((Savings) product).getAboutPrimeRate().split("\\\\"))
                        .build();
                ProductResponseVO productResponseVO = getProductResponseVO();
                productResponseVO.getResultData().add(savings);
                return productResponseVO;
            }else {
                Subscription subscription = (Subscription) product;
                SubscriptionResponseDTO subscriptionResponseDTO = SubscriptionResponseDTO.builder()
                        .productId(product.getProductId())
                        .category("SUBSCRIPTION")
                        .productName(product.getProductName())
                        .companyName(product.getCompanyName())
                        .companyImage(product.getCompanyImage())
                        .productURL(product.getProductURL())
                        .highRate(subscription.getHighRate())
                        .aboutRate(subscription.getAboutRate().split("\\\\n"))
                        .bound(subscription.getBound())
                        .purchase(subscription.getPurchase().split("\\\\n"))
                        .qualification(subscription.getQualification().split("\\\\n"))
                        .build();
                ProductResponseVO productResponseVO = getProductResponseVO();
                productResponseVO.getResultData().add(subscriptionResponseDTO);
                return productResponseVO;
        }
        }catch (NoSuchElementException e){
            ProductResponseVO productResponseVO = new ProductResponseVO();
            productResponseVO.setStatus("failed 상품이 존재하지 않습니다.");
            return productResponseVO;
        }
    }

    private ProductResponseVO getProductResponseVO() {
        ProductResponseVO productResponseVO = new ProductResponseVO();
        productResponseVO.setDateNum(1);
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
//        List<Product> result = new ArrayList<>();
//        String[] tags = tagString.split("&");
//        for (Product product : products) {
//            for (String tag : tags) {
//                if (product.getTags().contains(tag)) {
//                    result.add(product);
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
