package com.example.finance7.cart.controller;

import com.example.finance7.cart.service.CartService;
import com.example.finance7.cart.vo.CartVO;
import com.example.finance7.cart.vo.SimpleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public SimpleVO addCart(@RequestBody Long productId, Long memberId) {
        return cartService.addCart(productId, memberId);
    }

    @GetMapping("/cart")
    public CartVO selectAllCartProducts(@RequestBody Long productId) {
        return cartService.selectAllCartProducts(productId);
    }
}
