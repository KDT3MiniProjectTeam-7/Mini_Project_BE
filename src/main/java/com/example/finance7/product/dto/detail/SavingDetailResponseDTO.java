package com.example.finance7.product.dto.detail;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class SavingDetailResponseDTO extends ProductDetailResponseDTO{

    private String basicRate;
    private String primeRate;
    private String qualification;
    private String aboutPrimeRate;
}
