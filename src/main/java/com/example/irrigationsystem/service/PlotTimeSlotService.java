package com.example.irrigationsystem.service;

import com.example.irrigationsystem.model.Plot;
import com.example.irrigationsystem.model.PlotTimeSlot;
import com.example.irrigationsystem.repositories.PlotTimeSlotRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PlotTimeSlotService {
    private final PlotTimeSlotRepo plotTimeSlotRepo;

    @Transactional
    public PlotTimeSlot create(PlotTimeSlot plotTimeSlot) {
        return plotTimeSlotRepo.save(plotTimeSlot);
    }

}
