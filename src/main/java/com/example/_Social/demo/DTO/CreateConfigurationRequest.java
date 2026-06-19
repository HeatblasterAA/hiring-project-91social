package com.example._Social.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data

public class CreateConfigurationRequest {
    @NotBlank
    private String name;

    @NotEmpty
    private List<ConfigurationPartRequest> parts;
}
