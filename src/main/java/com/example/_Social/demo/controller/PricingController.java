package com.example._Social.demo.controller;

import com.example._Social.demo.DTO.PricingResponse;
import com.example._Social.demo.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    @GetMapping("/configurations/{id}/historical")
    public ResponseEntity<PricingResponse>
    calculateHistoricalPrice(
            @PathVariable Long id,
            @RequestParam LocalDate date
    ) {

        return ResponseEntity.ok(
                pricingService.calculateHistoricalPrice(
                        id,
                        date
                )
        );
    }
}