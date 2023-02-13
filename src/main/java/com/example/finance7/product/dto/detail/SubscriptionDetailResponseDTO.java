package com.example.finance7.product.dto.detail;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class SubscriptionDetailResponseDTO extends ProductDetailResponseDTO{

    private String highRate;
    private Integer limit;
    private String purchase;
    private String qualification;
}
