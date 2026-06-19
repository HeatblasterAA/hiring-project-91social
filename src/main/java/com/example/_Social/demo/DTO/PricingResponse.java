package com.example._Social.demo.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PricingResponse {
    private String configurationName;

    private List<ComponentPriceResponse> components;

    private BigDecimal grandTotal;
}

