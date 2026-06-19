package com.example._Social.demo.service;

import com.example._Social.demo.DTO.CreatePartRequest;
import com.example._Social.demo.DTO.PartResponse;
import com.example._Social.demo.entity.Part;
import com.example._Social.demo.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    public PartResponse createPart(CreatePartRequest request){
        Part part = Part.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .build();
        Part saved = partRepository.save(part);
        return PartResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .category(saved.getCategory())
                .description(saved.getDescription())
                .build();
    }

    public List<PartResponse> getAllParts(){
     return partRepository.findAll().stream()
             .map(part-> PartResponse.builder()
                     .id(part.getId())
                     .name(part.getName())
                     .description(part.getDescription())
                     .category(part.getCategory())
                     .build()).toList();
    }
}
