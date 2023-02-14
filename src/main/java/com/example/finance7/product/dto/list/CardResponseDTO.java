package com.example.finance7.product.dto.list;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class CardResponseDTO extends ProductResponseDTO{

    private String[] benefits;
    private Integer annualFee;
}
