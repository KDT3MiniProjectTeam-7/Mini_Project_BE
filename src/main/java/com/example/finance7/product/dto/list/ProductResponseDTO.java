package com.example.finance7.product.dto.list;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class ProductResponseDTO {

    private Long productId;
    private String category;
    private String productName;
    private String companyName;
    private String companyImage;
    private String thumbnail;
}
