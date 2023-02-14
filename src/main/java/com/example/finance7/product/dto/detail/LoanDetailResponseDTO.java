package com.example.finance7.product.dto.detail;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class LoanDetailResponseDTO extends ProductDetailResponseDTO{

    private String lowRate;
    private String highRate;
    private String bound;
    private String qualification;
}
