package com.example.demo.server.service;

import com.example.demo.server.entities.IntakeStatus;
import com.example.demo.server.entities.MedicationPlan;
import com.example.demo.server.repositories.IntakeStatusRepository;
import com.example.demo.server.repositories.MedicationPRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicationPlanImpl implements MedicationPlanInterface {

    @Autowired
    MedicationPRepository medicationPlanRepository;
    @Autowired
    IntakeStatusRepository intakeStatusRepository;
    @Override
    public void hello(String s) {
        System.out.println(s);
    }

    @Override
    public List<String> getPlan(Integer id) {
        List<MedicationPlan> medicationPlansList = medicationPlanRepository.findByPatient(id);
        List<String> planList= new ArrayList<String>();
        if(medicationPlansList.size()==0){
            planList.add("There are no medication plans for the moment!");
        }
        for (MedicationPlan p : medicationPlansList) {
            planList.add(p.getTreatment_period());
            planList.add(p.getIntervals());
        }
        return planList;
    }

    @Override
    public List<String> getMedication(Integer id){

        List<MedicationPlan> medicationPlansList = medicationPlanRepository.findByPatient(id);
        List<String> meds= new ArrayList<String>();
        List<String> meds2= new ArrayList<String>();
        for (MedicationPlan p : medicationPlansList) {
            String start = p.getTreatment_period().substring(0,10);
            String end = p.getTreatment_period().substring(13,p.getTreatment_period().length());
            Date dateStart;
            Date dateEnd;
            try{
                dateStart=new SimpleDateFormat("dd/MM/yyyy").parse(start);
                dateEnd=new SimpleDateFormat("dd/MM/yyyy").parse(end);

                SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyy");
                Date current= new Date(System.currentTimeMillis());

                if(dateStart.compareTo(current)<=0 && current.compareTo(dateEnd)<=0){

                    String[] parts = p.getIntervals().split(" ");
                    String med="";
                    for(String s: parts) {
                       // System.out.println(s);
                        if(s.length()>0) {
                            if (s.charAt(0) < '0' || s.charAt(0) > '9') {
                                if (!med.equals(""))
                                    meds.add(med);
                                med = "";
                                med = med + s;
                            } else {
                                med = med + "@";
                                med = med + s;
                            }
                        }
                    }
                    meds.add(med);
                }

            }catch(Exception e){
                System.out.println("Wrong format!");
                e.printStackTrace();
            }
        }

        for(String g: meds){
            String[] parts2 = g.split("@");
            if(parts2.length>2){
                for(int i=1; i<parts2.length; i++){
                    meds2.add(parts2[0]+"@"+parts2[i].replace(",", ""));

                }
            }else{
                meds2.add(g);
            }

        }

        for(String g: meds2) {
            System.out.println(g);
        }
        return meds2;
    }

    @Override
    public void saveTakenNotTaken(String medicine, Date date, Boolean status) {
        IntakeStatus is=new IntakeStatus(medicine, date, status);
        intakeStatusRepository.save(is);
    }
}
