package com.example._Social.demo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class ConfigurationPartRequest {
    @NotNull
    private Long partId;

    @NotNull
    private Integer quantity;

}


