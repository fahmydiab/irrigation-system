package com.example.irrigationsystem.service;

import com.example.irrigationsystem.model.TimeSlotDay;
import com.example.irrigationsystem.repositories.TimeSlotDayRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class TimeSlotDayService {
    private final TimeSlotDayRepo timeSlotDayRepo;

    @Transactional
    public TimeSlotDay createTimeSlotDay() {
        TimeSlotDay day = new TimeSlotDay();
        day.setStartDate(LocalDate.of(2022, Month.APRIL,1));
        day.setCreatedDate(LocalDateTime.of(2022,Month.APRIL,1,12,0));
        return timeSlotDayRepo.save(day);
    }
}
