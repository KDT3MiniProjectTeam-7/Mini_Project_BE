package com.example.finance7.cart.service.impl;

import com.example.finance7.cart.entity.Cart;
import com.example.finance7.cart.repository.CartRepository;
import com.example.finance7.cart.service.CartService;
import com.example.finance7.cart.vo.SimpleVO;
import com.example.finance7.member.entity.Member;
import com.example.finance7.member.service.MemberService;
import com.example.finance7.product.entity.Product;
import com.example.finance7.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final MemberService memberService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    /**
     * 장바구니 상품 추가
     * @param memberId
     * @param productId
     * @return
     */
    @Override
    public SimpleVO addCart(Long memberId, Long productId) {
        try {
            Member member = memberService.findMemberByMemberId(memberId);
            Product product = productService.findProductByProductId(productId);
            duplicateCheck(member, product);
            Cart item = Cart.builder()
                    .member(member)
                    .product(product)
                    .build();
            cartRepository.save(item);
        } catch (Exception e) {
            return SimpleVO.builder()
                    .status("failed:장바구니 추가에 실패했습니다.")
                    .build();
        }
        return SimpleVO.builder()
                .status("success")
                .build();
    }

    /**
     * 장바구니 상품 중복 체크
     * @param member
     * @param product
     * @throws Exception
     */
    private void duplicateCheck(Member member, Product product) throws Exception{
        if (cartRepository.existsByMemberAndProduct(member, product)) {
            throw new Exception();
        }
    }
}
