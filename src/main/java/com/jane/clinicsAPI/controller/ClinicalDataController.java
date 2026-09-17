package com.jane.clinicsAPI.controller;

import com.jane.clinicsAPI.dto.ClinicalDataDto;
import com.jane.clinicsAPI.model.ClinicalData;
import com.jane.clinicsAPI.model.Patient;
import com.jane.clinicsAPI.repository.ClinicalDataRepository;
import com.jane.clinicsAPI.repository.PatientRepository;
import com.jane.clinicsAPI.util.BMICalculator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.jane.clinicsAPI.util.BMICalculator.calculateBMI;

@RestController
@RequestMapping("/api")
@CrossOrigin
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

  @GetMapping("/clinicals/{id}/{componentName}")
  public List<ClinicalData> getClinicalData(@PathVariable("id") int patientId,
                                            @PathVariable("componentName") String componentName) {
//    Patient patient = patientRepository.findById(patientId).get();
    if(componentName.equalsIgnoreCase("bmi")){
      componentName = "hw";
    }
    List<ClinicalData> clinicalData = clinicalDataRepository.findByIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
    List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
    for (ClinicalData eachEntry : duplicateClinicalData) {
      calculateBMI(eachEntry, clinicalData);
    }
    return clinicalData;
  }
}
