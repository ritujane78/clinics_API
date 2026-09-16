package com.jane.clinicsAPI.repository;

import com.jane.clinicsAPI.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
