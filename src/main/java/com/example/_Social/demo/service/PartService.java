package com.example._Social.demo.service;

import com.example._Social.demo.entity.Part;
import com.example._Social.demo.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    public Part createPart(Part part){
        return partRepository.save(part);
    }

    public List<Part> getAllParts(){
        return partRepository.findAll();
    }
}
