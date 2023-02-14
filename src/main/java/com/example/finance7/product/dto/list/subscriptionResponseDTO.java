package com.example.finance7.product.dto.list;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@Getter
@Setter
public class subscriptionResponseDTO extends ProductResponseDTO {

    private String highRate;
    private String aboutRate;
    private Integer limit;
    private String purchase;
    private String qualification;
}
