package com.example.lab3_jdbc.repository;

import com.example.lab3_jdbc.model.Flight; // Імпортуємо нову модель Flight
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}