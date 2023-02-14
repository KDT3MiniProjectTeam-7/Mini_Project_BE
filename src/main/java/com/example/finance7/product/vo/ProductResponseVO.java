package com.example.finance7.product.vo;

import com.example.finance7.product.dto.list.ProductResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class ProductResponseVO {

    private String status;
    private Integer dateNum;
    private List<ProductResponseDTO> resultData;
}
