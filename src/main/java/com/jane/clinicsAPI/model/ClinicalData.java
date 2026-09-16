package com.jane.clinicsAPI.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class ClinicalData {
  @Id
  private int id;

  private String componentName;
  private String componentValue;
  private Timestamp measuredDateTime;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.EAGER)
  private List<ClinicalData> clinicalData;

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
