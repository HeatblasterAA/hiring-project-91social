package com.example._Social.demo.repository;

import com.example._Social.demo.entity.PartPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartPriceHistoryRepository extends JpaRepository<PartPriceHistory,Long> {

    List<PartPriceHistory> findByPartIdOrderByEffectiveFromDesc(Long partId);
}
