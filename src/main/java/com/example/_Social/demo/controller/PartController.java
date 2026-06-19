package com.example._Social.demo.controller;

import com.example._Social.demo.DTO.CreatePartPriceRequest;
import com.example._Social.demo.DTO.CreatePartRequest;
import com.example._Social.demo.DTO.PartResponse;
import com.example._Social.demo.entity.Part;
import com.example._Social.demo.service.PartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;

    @PostMapping
    public ResponseEntity<PartResponse> createPart( @Valid @RequestBody CreatePartRequest request ) {
        return ResponseEntity.ok(
                partService.createPart(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<PartResponse>> getAllParts() {
        return ResponseEntity.ok(
                partService.getAllParts()
        );
    }

    @PostMapping("/{partId}/prices")
    public ResponseEntity<Void> addPrice(@PathVariable Long partId, @Valid @RequestBody CreatePartPriceRequest request){
        partService.addPrice(partId,request);
        return ResponseEntity.ok().build();
    }
}
