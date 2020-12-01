package com.example.demo.server.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MedicationPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer ID;

    @Column(name = "intervals", nullable = false)
    private String intervals;

    @Column(name = "treatment_period", nullable = false)
    private String treatment_period;

    @Column
    private Integer patientId;


    public MedicationPlan(){

    }

    public MedicationPlan(String intervals, String treatment_period, Integer patient) {
        this.intervals = intervals;
        this.treatment_period = treatment_period;
        this.patientId = patient;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public String getTreatment_period() {
        return treatment_period;
    }

    public void setTreatment_period(String treatment_period) {
        this.treatment_period = treatment_period;
    }

    public Integer getPatient() {
        return patientId;
    }

    public void setPatient(Integer patient) {
        this.patientId = patient;
    }

}
