package com.jane.clinicsAPI.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Patient {
  @Id
  private int id;

  private String firstName;
  private String lastName;
  private int age;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.EAGER)
  private List<ClinicalData> clinicalData;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
