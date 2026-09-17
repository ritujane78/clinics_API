package com.jane.clinicsAPI.repository;

import com.jane.clinicsAPI.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
  List<ClinicalData> findByIdAndComponentNameOrderByMeasuredDateTime(int id, String componentName);
}
