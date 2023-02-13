package com.example.finance7.product.dto.detail;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class ProductDetailResponseDTO {

    private String category;
    private String productName;
    private String companyName;
    private String companyImage;
    private String productURL;
}
