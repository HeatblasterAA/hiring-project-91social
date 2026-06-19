package com.example._Social.demo.controller;

import com.example._Social.demo.DTO.ConfigurationResponse;
import com.example._Social.demo.DTO.CreateConfigurationRequest;
import com.example._Social.demo.service.ConfigurationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ConfigurationResponse>>
    getAllConfigurations() {

        return ResponseEntity.ok(
                configurationService
                        .getAllConfigurations()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConfigurationResponse>
    getConfigurationById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                configurationService
                        .getConfigurationById(id)
        );
    }
}
