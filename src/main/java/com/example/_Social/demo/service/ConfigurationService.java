package com.example._Social.demo.service;

import com.example._Social.demo.DTO.ConfigurationPartRequest;
import com.example._Social.demo.DTO.ConfigurationResponse;
import com.example._Social.demo.DTO.CreateConfigurationRequest;
import com.example._Social.demo.entity.ConfigurationPart;
import com.example._Social.demo.entity.CycleConfiguration;
import com.example._Social.demo.entity.Part;
import com.example._Social.demo.repository.ConfigurationPartRepository;
import com.example._Social.demo.repository.CycleConfigurationRepository;
import com.example._Social.demo.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigurationService {


    private final PartRepository partRepository;
    private final ConfigurationPartRepository configurationPartRepository;
    private  final CycleConfigurationRepository cycleConfigurationRepository;

    public ConfigurationResponse createConfiguration(CreateConfigurationRequest request){
        CycleConfiguration configuration =
                CycleConfiguration.builder()
                        .name(request.getName())
                        .build();

        configuration =
                cycleConfigurationRepository.save(configuration);

        for (ConfigurationPartRequest partRequest : request.getParts()) {

            Part part =
                    partRepository.findById(
                                    partRequest.getPartId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Part not found"
                                    ));

            ConfigurationPart configurationPart =
                    ConfigurationPart.builder()
                            .configuration(configuration)
                            .part(part)
                            .quantity(partRequest.getQuantity())
                            .build();

            configurationPartRepository.save(
                    configurationPart
            );
        }

        return ConfigurationResponse.builder()
                .id(configuration.getId())
                .name(configuration.getName())
                .build();
    }
}