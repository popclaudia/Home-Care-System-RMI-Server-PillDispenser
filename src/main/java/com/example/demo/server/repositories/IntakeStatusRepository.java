package com.example.demo.server.repositories;

import com.example.demo.server.entities.IntakeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntakeStatusRepository extends JpaRepository<IntakeStatus, Integer> {
}
