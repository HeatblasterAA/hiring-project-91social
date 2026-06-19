package com.example._Social.demo.entity;


import com.example._Social.demo.enums.PartCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="parts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private PartCategory category;

    private String description;



}
