package by.woodygray.ws.productmicroservice.service.dto;

import java.math.BigDecimal;

public record CreateProductDto(
        String title,
        BigDecimal price,
        Integer quantity
) {
}
