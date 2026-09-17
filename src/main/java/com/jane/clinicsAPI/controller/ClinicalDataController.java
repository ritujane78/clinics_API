package com.jane.clinicsAPI.controller;

import com.jane.clinicsAPI.dto.ClinicalDataDto;
import com.jane.clinicsAPI.model.ClinicalData;
import com.jane.clinicsAPI.model.Patient;
import com.jane.clinicsAPI.repository.ClinicalDataRepository;
import com.jane.clinicsAPI.repository.PatientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {

  private ClinicalDataRepository clinicalDataRepository;
  private PatientRepository patientRepository;

  public ClinicalDataController(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository) {
    this.clinicalDataRepository = clinicalDataRepository;
    this.patientRepository = patientRepository;
  }

  @PostMapping("/clinicals")
  public ClinicalData saveClinicalData(@RequestBody ClinicalDataDto clinicalDataDto){

    Patient patient = patientRepository.findById(clinicalDataDto.getPatientId()).get();
    ClinicalData clinicalData = new ClinicalData();
    clinicalData.setComponentName(clinicalDataDto.getComponentName());
    clinicalData.setComponentValue(clinicalDataDto.getComponentValue());
    clinicalData.setPatient(patient);

    return clinicalDataRepository.save(clinicalData);
  }
}
