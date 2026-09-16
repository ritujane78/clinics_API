package com.jane.clinicsAPI.repository;

import com.jane.clinicsAPI.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
}
