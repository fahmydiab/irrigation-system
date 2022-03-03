package com.example.irrigationsystem.service;


import com.example.irrigationsystem.model.TimeSlot;
import com.example.irrigationsystem.repositories.TimeSlotRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;


@Service
@RequiredArgsConstructor
public class TimeSlotService {
    private final TimeSlotRepo timeSlotRepo;

    public TimeSlot createTimeSlot() {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setWaterAmountPerSlot(new BigDecimal(100));
        timeSlot.setCreatedDate(LocalDateTime.of(2022, Month.APRIL,1,12,0));
        return timeSlotRepo.save(timeSlot);
    }
}
