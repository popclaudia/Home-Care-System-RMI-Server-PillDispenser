package com.example.demo.server.service;

import java.util.Date;
import java.util.List;

public interface MedicationPlanInterface {

    void hello(String s);

    List<String> getPlan(Integer id);
    List<String> getMedication(Integer id);
    void saveTakenNotTaken(String medicine, Date date, Boolean status);
}
