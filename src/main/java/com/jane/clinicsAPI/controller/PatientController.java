package com.jane.clinicsAPI.controller;

import com.jane.clinicsAPI.model.ClinicalData;
import com.jane.clinicsAPI.model.Patient;
import com.jane.clinicsAPI.repository.PatientRepository;
import com.jane.clinicsAPI.util.BMICalculator;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.jane.clinicsAPI.util.BMICalculator.calculateBMI;

@RestController
@RequestMapping("/api")
@CrossOrigin
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
  public Patient analyse(@PathVariable("id") int id) {
    Patient patient = patientRepository.findById(id).get();
    List<ClinicalData> clinicalData = patient.getClinicalData();
    List<ClinicalData> duplicateClinicalData = new ArrayList<>(patient.getClinicalData());
    for (ClinicalData eachEntry : duplicateClinicalData) {
      if (filters.containsKey(eachEntry.getComponentName())) {
        clinicalData.remove(eachEntry);
        continue;
      } else {
        filters.put(eachEntry.getComponentName(), filters.get(eachEntry.getComponentName()));
      }
      calculateBMI(eachEntry, clinicalData);
    }
    return patient;
  }
}
