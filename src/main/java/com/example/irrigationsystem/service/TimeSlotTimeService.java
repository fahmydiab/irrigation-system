package com.example.irrigationsystem.service;

import com.example.irrigationsystem.model.TimeSlotTime;
import com.example.irrigationsystem.repositories.TimeSlotTimeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class TimeSlotTimeService {
    private final TimeSlotTimeRepo timeSlotTimeRepo;

    @Transactional
    public TimeSlotTime createTimeSlotTime() {
        TimeSlotTime time = new TimeSlotTime();
        time.setStartTime(LocalTime.of(9,0));
        time.setCreatedDate(LocalDateTime.of(2022, Month.APRIL,1,12,0));
        return timeSlotTimeRepo.save(time);
    }
}
