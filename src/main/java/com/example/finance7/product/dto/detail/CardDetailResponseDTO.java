package com.example.finance7.product.dto.detail;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class CardDetailResponseDTO extends ProductDetailResponseDTO {

    private String[] benefits;
    private Integer annualFee;
}

