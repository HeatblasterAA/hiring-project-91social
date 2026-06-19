package com.example._Social.demo.controller;

import com.example._Social.demo.entity.Part;
import com.example._Social.demo.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;

    @PostMapping
    public Part createPart(@RequestBody Part part){
        return partService.createPart(part);
    }

    @GetMapping
    public List<Part> getAllParts(){
        return partService.getAllParts();
    }
}
