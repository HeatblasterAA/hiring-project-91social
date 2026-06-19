package com.example._Social.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "configuration_part")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigurationPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="configuration_id", nullable = false)
    private CycleConfiguration configuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="part_id", nullable = false)
    private Part part;

    @Column(nullable = false)
    private Integer quantity;
}
