package com.example.irrigationsystem.repositories;

import com.example.irrigationsystem.model.PlotTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotTimeSlotRepo extends JpaRepository<PlotTimeSlot,Integer> {
}
