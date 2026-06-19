package com.example._Social.demo.DTO;

import com.example._Social.demo.enums.PartCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePartRequest {

    @NotBlank
    private String name;
    @NotNull
    private PartCategory category;
    private String description;
}
