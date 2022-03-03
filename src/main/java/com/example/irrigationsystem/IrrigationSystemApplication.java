package com.example.irrigationsystem;

import com.example.irrigationsystem.model.*;
import com.example.irrigationsystem.service.PlotService;
import com.example.irrigationsystem.service.TimeSlotDayService;
import com.example.irrigationsystem.service.TimeSlotService;
import com.example.irrigationsystem.service.TimeSlotTimeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class IrrigationSystemApplication implements CommandLineRunner {

    private final TimeSlotTimeService timeSlotTimeService;
    private final TimeSlotDayService timeSlotDayService;
    private final TimeSlotService timeSlotService;
    private final PlotService plotService;
    private static final Logger log = LoggerFactory.getLogger(IrrigationSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IrrigationSystemApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        seedData();
    }

    private void seedData() {
        try {
            plotService.createPlot();
        }
        catch (Exception e) {
            log.warn("Plot already exists");
        }
        try {
            timeSlotService.createTimeSlot();
        }
        catch (Exception e) {
            log.warn("Time Slot already exists");
        }
//        try {
//            timeSlotTimeService.createTimeSlotTime();
//        } catch (Exception e) {
//            log.warn("Time Slot Time already exists");
//        }
//        try {
//            timeSlotDayService.createTimeSlotDay();
//        } catch (Exception e) {
//            log.warn("Time Slot day already exists");
//        }

    }

}
