package com.example.irrigationsystem.repositories;

import com.example.irrigationsystem.model.TimeSlotDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotDayRepo extends JpaRepository<TimeSlotDay, Integer> {
}
