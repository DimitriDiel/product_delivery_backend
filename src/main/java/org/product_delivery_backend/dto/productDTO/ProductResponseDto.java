package org.product_delivery_backend.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String quantity;
    private String productCode;
    private String description;
    private String photoLink;

}
