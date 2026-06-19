package com.example._Social.demo.service;

import com.example._Social.demo.DTO.ComponentPriceResponse;
import com.example._Social.demo.DTO.PricingResponse;
import com.example._Social.demo.entity.ConfigurationPart;
import com.example._Social.demo.entity.CycleConfiguration;
import com.example._Social.demo.entity.PartPriceHistory;
import com.example._Social.demo.exception.ResourceNotFoundException;
import com.example._Social.demo.repository.ConfigurationPartRepository;
import com.example._Social.demo.repository.CycleConfigurationRepository;
import com.example._Social.demo.repository.PartPriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PricingService {
    private final CycleConfigurationRepository cycleConfigurationRepository;
    private final PartPriceHistoryRepository partPriceHistoryRepository;
    private final ConfigurationPartRepository configurationPartRepository;


    public PricingResponse calculatePrice(Long configurationId ){
        CycleConfiguration configuration =
                cycleConfigurationRepository
                        .findById(configurationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Configuration not found"
                                ));
        List<ConfigurationPart> configurationParts =
                configurationPartRepository
                        .findByConfigurationId(
                                configurationId
                        );
        List<ComponentPriceResponse> components =
                new ArrayList<>();

        BigDecimal grandTotal =
                BigDecimal.ZERO;
        for (ConfigurationPart cp : configurationParts) {
            PartPriceHistory latestPrice =
                    partPriceHistoryRepository
                            .findTopByPartIdOrderByEffectiveFromDesc(
                                    cp.getPart().getId()
                            )
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Price not found"
                                    ));
            BigDecimal componentTotal =
                    latestPrice.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            cp.getQuantity()
                                    )
                            );
            grandTotal= grandTotal.add(componentTotal);
            ComponentPriceResponse component =
                    ComponentPriceResponse.builder()
                            .partName(
                                    cp.getPart().getName()
                            )
                            .unitPrice(
                                    latestPrice.getPrice()
                            )
                            .quantity(
                                    cp.getQuantity()
                            )
                            .totalPrice(
                                    componentTotal
                            )
                            .build();

            components.add(component);
        }
        return PricingResponse.builder()
                .configurationName(
                        configuration.getName()
                )
                .components(components)
                .grandTotal(grandTotal)
                .build();


    }
    public PricingResponse calculateHistoricalPrice(
            Long configurationId,
            LocalDate date
    ) {

        CycleConfiguration configuration =
                cycleConfigurationRepository
                        .findById(configurationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Configuration not found"
                                ));

        List<ConfigurationPart> configurationParts =
                configurationPartRepository
                        .findByConfigurationId(
                                configurationId
                        );

        List<ComponentPriceResponse> components =
                new ArrayList<>();

        BigDecimal grandTotal =
                BigDecimal.ZERO;

        for (ConfigurationPart cp : configurationParts) {

            PartPriceHistory latestPrice =
                    partPriceHistoryRepository
                            .findTopByPartIdAndEffectiveFromLessThanEqualOrderByEffectiveFromDesc(
                                    cp.getPart().getId(),
                                    date
                            )
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Price not found"
                                    ));

            BigDecimal componentTotal =
                    latestPrice.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            cp.getQuantity()
                                    )
                            );

            grandTotal =
                    grandTotal.add(componentTotal);

            ComponentPriceResponse component =
                    ComponentPriceResponse.builder()
                            .partName(
                                    cp.getPart().getName()
                            )
                            .unitPrice(
                                    latestPrice.getPrice()
                            )
                            .quantity(
                                    cp.getQuantity()
                            )
                            .totalPrice(
                                    componentTotal
                            )
                            .build();

            components.add(component);
        }

        return PricingResponse.builder()
                .configurationName(
                        configuration.getName()
                )
                .components(components)
                .grandTotal(grandTotal)
                .build();
    }
}
