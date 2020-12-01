package com.example.demo.server.repositories;


import com.example.demo.server.entities.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicationPRepository extends JpaRepository<MedicationPlan, Integer> {

    @Query(value = "SELECT p " +
            "FROM MedicationPlan p " +
            "WHERE p.patientId = :id ")
    List<MedicationPlan> findByPatient(@Param("id") Integer id);
}