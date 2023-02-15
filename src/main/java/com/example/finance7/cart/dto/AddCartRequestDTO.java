package com.example.finance7.cart.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AddCartRequestDTO {
    private Long productId;
}
