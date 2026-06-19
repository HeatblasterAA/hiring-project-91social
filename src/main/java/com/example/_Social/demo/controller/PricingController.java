package com.example._Social.demo.controller;

import com.example._Social.demo.DTO.PricingResponse;
import com.example._Social.demo.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pricing")
@RequiredArgsConstructor
public class PricingController {
    private final PricingService pricingService;

    @GetMapping("/configurations/{id}")
    public ResponseEntity<PricingResponse> calculatePrice(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                pricingService.calculatePrice(id)
        );
    }
}