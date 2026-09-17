package com.jane.clinicsAPI.controller;

import com.jane.clinicsAPI.model.ClinicalData;
import com.jane.clinicsAPI.model.Patient;
import com.jane.clinicsAPI.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PatientController {
  Map<String, String> filters = new HashMap<>();
  private PatientRepository patientRepository;

  public PatientController(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  @GetMapping("/patients")
  public List<Patient> findAll() {
    return patientRepository.findAll();
  }

  @GetMapping("/patients/{id}")
  public Patient findById(@PathVariable int id) {
    return patientRepository.findById(id).orElse(null);
  }

  @PostMapping("/patients")
  public Patient create(@RequestBody Patient patient) {
    return patientRepository.save(patient);
  }

  @GetMapping("/patients/analyze/{id}")
  public Patient analyze(@PathVariable int id) {

    Patient patient = patientRepository.findById(id).orElse(null);

    if (patient == null) {
      return null;
    }

    List<ClinicalData> clinicalData = patient.getClinicalData();

    Map<String, String> filters = new HashMap<>();
    List<ClinicalData> uniqueEntries = new ArrayList<>();

    ClinicalData hwData = null;

    for (ClinicalData eachEntry : clinicalData) {

      // Ignore duplicates
      if (filters.containsKey(eachEntry.getComponentName())) {
        continue;
      }

      filters.put(
        eachEntry.getComponentName(),
        eachEntry.getComponentValue()
      );

      uniqueEntries.add(eachEntry);

      if ("hw".equals(eachEntry.getComponentName())) {
        hwData = eachEntry;
      }
    }

    // Calculate BMI
    if (hwData != null) {

      String[] heightAndWeight =
        hwData.getComponentValue().split("/");

      if (heightAndWeight.length == 2
        && !heightAndWeight[0].isEmpty()
        && !heightAndWeight[1].isEmpty()) {
        double heightInMeters = Double.parseDouble(heightAndWeight[0]) * 0.4536; // Convert height from inches to meters
        double weightInKg = Double.parseDouble(heightAndWeight[1]);
        double bmi =
          weightInKg / (heightInMeters * heightInMeters);

        ClinicalData bmiData = new ClinicalData();
        bmiData.setComponentName("bmi");
        bmiData.setComponentValue(String.valueOf(bmi));

        uniqueEntries.add(bmiData);
      }
    }

    patient.setClinicalData(uniqueEntries);

    return patient;
  }
}
