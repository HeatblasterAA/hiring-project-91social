package com.example._Social.demo.DTO;

import com.example._Social.demo.enums.PartCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartResponse {
    private  Long id;
    private String name;
    private PartCategory category;
    private String description;
}
