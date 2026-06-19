package com.example._Social.demo.controller;

import com.example._Social.demo.DTO.ConfigurationResponse;
import com.example._Social.demo.DTO.CreateConfigurationRequest;
import com.example._Social.demo.service.ConfigurationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configurations")
@RequiredArgsConstructor
public class ConfigurationController {
    private final ConfigurationService configurationService;

    @PostMapping
    public ResponseEntity<ConfigurationResponse>
    createConfiguration(
            @Valid
            @RequestBody
            CreateConfigurationRequest request
    ) {

        return ResponseEntity.ok(
                configurationService
                        .createConfiguration(request)
        );
    }
}
