package com.example.finance7.product.vo;

import com.example.finance7.product.dto.ProductResponseDTO;
import lombok.*;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class ProductResponseVO {

    private String status;
    private Integer dateNum;
    private List<ProductResponseDTO> resultData;
}
