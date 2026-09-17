package com.jane.clinicsAPI.util;

import com.jane.clinicsAPI.model.ClinicalData;
import com.jane.clinicsAPI.model.Patient;

import java.util.List;

public class BMICalculator {
  public static void calculateBMI(ClinicalData eachEntry, List<ClinicalData> clinicalDataList) {
    if (eachEntry.getComponentName().equals("hw")) {
      String[] heightAndWeight = eachEntry.getComponentValue().split("/");
      if (heightAndWeight != null && heightAndWeight.length > 1) {
        float feetToMetres = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
        float BMI = Float.parseFloat(heightAndWeight[1]) / (feetToMetres * feetToMetres);
        ClinicalData bmiEntry = new ClinicalData();
        bmiEntry.setComponentName("BMI");
        bmiEntry.setComponentValue(Float.toString(BMI));
        clinicalDataList.add(bmiEntry);
      }
    }
  }
}
