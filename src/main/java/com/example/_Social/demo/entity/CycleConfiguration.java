package com.example._Social.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="cycle_configuration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CycleConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean active = true;


}
