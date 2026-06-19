package com.example._Social.demo.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigurationResponse {
    private Long id;
    private String name;
}
