package com.example.irrigationsystem.repositories;

import com.example.irrigationsystem.model.PlotTimeSlot;
import com.example.irrigationsystem.model.PlotTimeSlotEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface PlotTimeSlotRepo extends JpaRepository<PlotTimeSlot,Integer> {

    Set<PlotTimeSlot> findAllByStartDateTimeLessThanEqualAndStatus(LocalDateTime date, PlotTimeSlotEnum status);
}
