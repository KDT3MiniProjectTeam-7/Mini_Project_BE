package com.example.finance7.cart.service;

import com.example.finance7.cart.vo.CartVO;
import com.example.finance7.cart.vo.SimpleVO;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    SimpleVO addCart(Long memberId, Long productId);

    CartVO selectAllCartProducts(Long memberId);
}
