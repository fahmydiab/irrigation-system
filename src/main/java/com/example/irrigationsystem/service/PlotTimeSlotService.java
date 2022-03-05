package com.example.irrigationsystem.service;

import com.example.irrigationsystem.model.PlotTimeSlot;
import com.example.irrigationsystem.model.PlotTimeSlotEnum;
import com.example.irrigationsystem.repositories.PlotTimeSlotRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlotTimeSlotService {
    private final PlotTimeSlotRepo plotTimeSlotRepo;

    @Transactional
    public PlotTimeSlot create(PlotTimeSlot plotTimeSlot) {
        plotTimeSlot.setCreatedDate(LocalDate.now());
        return plotTimeSlotRepo.save(plotTimeSlot);
    }

    @Transactional
    public Set<PlotTimeSlot> fetchAllAvailableInitSlots(LocalDateTime date){
        return plotTimeSlotRepo.findAllByStartDateTimeLessThanEqualAndStatus(date,PlotTimeSlotEnum.INIT);
    }

}
