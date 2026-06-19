package com.example._Social.demo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreatePartPriceRequest {
    @NotNull
    private BigDecimal price;

    @NotNull
    private LocalDate effectiveFrom;
}
