package com.jane.clinicsAPI.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "clinicaldata")
public class ClinicalData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String componentName;
  private String componentValue;
  private Timestamp measuredDateTime;

  @ManyToOne
  @JoinColumn(name = "patient_id", nullable = false)
  private Patient patient;

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getComponentName() {
    return componentName;
  }

  public void setComponentName(String componentName) {
    this.componentName = componentName;
  }

  public String getComponentValue() {
    return componentValue;
  }

  public void setComponentValue(String componentValue) {
    this.componentValue = componentValue;
  }

  public Timestamp getMeasuredDateTime() {
    return measuredDateTime;
  }

  public void setMeasuredDateTime(Timestamp measuredDateTime) {
    this.measuredDateTime = measuredDateTime;
  }
}
