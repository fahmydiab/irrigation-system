package com.example.irrigationsystem.repositories;

import com.example.irrigationsystem.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepo extends JpaRepository<TimeSlot, Integer> {
}
