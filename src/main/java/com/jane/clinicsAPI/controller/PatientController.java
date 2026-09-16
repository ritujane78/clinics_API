package com.jane.clinicsAPI.controller;

import com.jane.clinicsAPI.model.Patient;
import com.jane.clinicsAPI.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {
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
}
