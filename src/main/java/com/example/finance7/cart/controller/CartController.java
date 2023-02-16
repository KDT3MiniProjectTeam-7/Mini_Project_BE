package com.example.finance7.cart.controller;

import com.example.finance7.cart.dto.CartRequestDTO;
import com.example.finance7.cart.dto.DeleteResponseDTO;
import com.example.finance7.cart.service.CartService;
import com.example.finance7.cart.vo.CartVO;
import com.example.finance7.cart.vo.SimpleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public SimpleVO addCart(@RequestBody CartRequestDTO requestDTO) {
        return cartService.addCart(requestDTO.getProductId());
    }

    @GetMapping("/cart")
    public CartVO selectAllCartProducts() {
        return cartService.selectAllCartProducts();
    }

    @DeleteMapping("/cart")
    public SimpleVO deleteItem(@RequestBody CartRequestDTO requestDTO) {
        return cartService.deleteItem(requestDTO.getProductId());
    }

    @DeleteMapping("/cart/all")
    public DeleteResponseDTO deleteAllItems() {
        return cartService.deleteAllItems();
    }
}
