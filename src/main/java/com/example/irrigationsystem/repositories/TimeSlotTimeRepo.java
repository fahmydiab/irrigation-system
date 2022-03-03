package com.example.irrigationsystem.repositories;

import com.example.irrigationsystem.model.TimeSlotTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotTimeRepo extends JpaRepository<TimeSlotTime, Integer> {
}
