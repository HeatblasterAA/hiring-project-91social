package com.example._Social.demo.repository;

import com.example._Social.demo.entity.ConfigurationPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationPartRepository extends JpaRepository<ConfigurationPart, Long> {

    List<ConfigurationPart>findByConfigurationId(Long configurationId);
}
